package com.cn.test.rpc.netty.client.proxy;

import com.cn.test.rpc.netty.client.ClientNetty;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-10-22 11:26:00
 * @description :
 */
public class ClientProxy implements InvocationHandler {
    private String host;
    private int port;

    public ClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ClientNetty clientNetty = new ClientNetty(host, port);
        return null;
    }
}
