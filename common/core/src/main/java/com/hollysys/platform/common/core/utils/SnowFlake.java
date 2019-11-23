package com.hollysys.platform.common.core.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SnowFlake {
    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;   //机器标识占用的位数
    private final static long DATACENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = MACHINE_LEFT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;


    private static int BACKUP_COUNT = 2;
    private static final long WORKER_ID_MAX_VALUE = -1L ^ (1L << MAX_SEQUENCE) / (BACKUP_COUNT + 1);

    //数据中心
    private long datacenterId;

    //机器标识
    private static long machineId;

    //序列号
    private long sequence = 0L;

    //上一次时间戳
    private long lastStmp = -1L;

    private static final long MAX_BACKWARD_MS = 50;

    /**
     * 保留workerId和lastTime, 以及备用workerId和其对应的lastTime
     */
    private static Map<Long, Long> workerIdLastTimeMap = new ConcurrentHashMap<>();

    static {
        // 初始化workerId和其所有备份workerId与lastTime
        // 假设 machineId 为 0 且 BACKUP_AMOUNT 为4, 那么map的值为: {0:0L, 256:0L, 512:0L, 768:0L}
        // 假设 machineId 为 2 且 BACKUP_AMOUNT 为4, 那么map的值为: {2:0L, 258:0L, 514:0L, 770:0L}
        for (int i = 0; i <= BACKUP_COUNT; i++) {
            workerIdLastTimeMap.put(machineId + (i * WORKER_ID_MAX_VALUE), 0L);
        }
    }

    private static class SnowFlakeHolder {
        protected static final SnowFlake INSTANCE = new SnowFlake(2, 5);
    }


    private SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        SnowFlake.machineId = machineId;
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            System.err.println("Clock moved backwards.  Refusing to generate id");

            // 如果时钟回拨在可接受范围内, 等待
            if (lastStmp - currStmp < MAX_BACKWARD_MS) {
                try {
                    Thread.sleep(lastStmp - currStmp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                tryGenerateKeyOnBackup(currStmp);
            }
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = waitUntilNextTime();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        // 更新map中保存的 machineId 对应的lastTime
        workerIdLastTimeMap.put(SnowFlake.machineId, lastStmp);

        return (currStmp - START_STMP) << TIMESTMP_LEFT
                | datacenterId << DATACENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    /**
     * 尝试在workerId的备份workerId上生成
     * @param currentMillis 当前时间
     */
    private long tryGenerateKeyOnBackup(long currentMillis) {
        System.out.println("try GenerateKey OnBackup, map:" + workerIdLastTimeMap);

        // 遍历所有workerId(包括备用workerId, 查看哪些workerId可用)
        for (Map.Entry<Long, Long> entry : workerIdLastTimeMap.entrySet()) {
            SnowFlake.machineId = entry.getKey();
            // 取得备用workerId的lastTime
            Long tempLastTime = entry.getValue();
            lastStmp = tempLastTime == null ? 0L : tempLastTime;

            // 如果找到了合适的workerId
            if (lastStmp <= currentMillis) {
                return lastStmp;
            }
        }

        // 如果所有 machineId 以及备用 machineId 都处于时钟回拨, 那么抛出异常
        throw new IllegalStateException("Clock is moving backwards, current time is " + currentMillis + " milliseconds, workerId map = " + workerIdLastTimeMap);
    }

    /**
     * @return
     */
    private long waitUntilNextTime() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    public static long id() {
        return SnowFlakeHolder.INSTANCE.nextId();
    }

}
