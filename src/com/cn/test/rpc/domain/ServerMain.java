package com.cn.test.rpc.domain;

import com.cn.test.rpc.netty.server.ServerNetty;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-10-22 10:35:00
 * @description :
 */
public class ServerMain {
    public static void main(String[] args) {
        new ServerNetty(1234);
    }
}
