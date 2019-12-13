package com.hollysys.platform.rpc.proxy.server.entity;

import lombok.Data;

@Data
public class RpcProxyEntity {
    private String[] source;

    private String[] target;
}
