package com.hollysys.platform.rpc.proxy.server.config;

import com.hollysys.platform.rpc.proxy.api.provider.RpcProxyProvider;
import com.hollysys.platform.rpc.proxy.server.service.RpcProxyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ThriftServeConfig {
    private int port = 25908;
    private int minThreads = 10;
    private int maxThreads = 10;

    private TBinaryProtocol.Factory protocolFactory;
    private TTransportFactory transportFactory;

    @Autowired
    private RpcProxyService rpcProxyService;

    public void init() {
        protocolFactory = new TBinaryProtocol.Factory();
        transportFactory = new TTransportFactory();
    }

    public TServer start() {
        RpcProxyProvider.Processor processor = new RpcProxyProvider.Processor(rpcProxyService);
        init();
        try {
            TServerTransport transport = new TServerSocket(port);
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(transport);
            tArgs.processor(processor);
            tArgs.protocolFactory(protocolFactory);
            tArgs.transportFactory(transportFactory);
            tArgs.minWorkerThreads(minThreads);
            tArgs.maxWorkerThreads(maxThreads);
            TServer server = new TThreadPoolServer(tArgs);
            log.info("thrift服务启动成功, 端口={}", port);
            server.serve();
            return server;
        } catch (Exception e) {
            log.error("thrift服务启动失败", e);
            return null;
        }

    }
}
