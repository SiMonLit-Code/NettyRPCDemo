package com.cn.test.rpc.domain;

import com.cn.test.fib.FibRequest;
import com.cn.test.rpc.netty.client.ClientNetty;
import com.cn.test.rpc.netty.client.proxy.ClientProxy;

import java.lang.reflect.Proxy;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-10-22 10:36:00
 * @description :
 */
public class ClientMain {
    public static void main(String[] args) {
        new Thread(() -> {
            FibRequest fibRequest = refer(FibRequest.class, "127.0.0.1", 1234);
            long handle = fibRequest.handle(5);
            System.out.println(handle);
        }).start();
    }

    /**
     * 代理
     * @param clazz
     * @param host
     * @param port
     * @param <T>
     * @return
     */
    public static <T> T refer(Class<?> clazz, String host, int port){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new ClientProxy(host, port));
    }
}
