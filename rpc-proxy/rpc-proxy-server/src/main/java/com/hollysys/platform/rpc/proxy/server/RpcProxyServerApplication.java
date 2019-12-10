package com.hollysys.platform.rpc.proxy.server;

import com.hollysys.platform.rpc.proxy.api.provider.RpcProxyProvider;
import com.hollysys.platform.rpc.proxy.server.service.RpcProxyService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RpcProxyServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RpcProxyServerApplication.class, args);
        // 定义服务器使用的socket类型
        TNonblockingServerSocket tNonblockingServerSocket = null;
        try {
            tNonblockingServerSocket = new TNonblockingServerSocket(25908);
            // 创建服务器参数
            THsHaServer.Args arg = new THsHaServer.Args(tNonblockingServerSocket).minWorkerThreads(2).maxWorkerThreads(4);
            // 请求处理器
            RpcProxyProvider.Processor<RpcProxyService> processor = new RpcProxyProvider.Processor<>(new RpcProxyService());
            // 配置传输数据的格式
            arg.protocolFactory(new TCompactProtocol.Factory());
            // 配置数据传输的方式
            arg.transportFactory(new TFramedTransport.Factory());
            // 配置处理器用来处理rpc请求
            arg.processorFactory(new TProcessorFactory(processor));
            // 本示例中使用半同步半异步方式的服务器模型
            TServer server = new THsHaServer(arg);
            System.out.println("Thrift Server Started!");
            // 启动服务
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }

    }
}
