package com.cn.test.echorpc.service;

import com.cn.test.echorpc.service.netty.UserServiceImpl;
import com.cn.test.echorpc.service.echoServer.EchoServerImpl;
import com.cn.test.echorpc.service.echoServer.IEchoServer;

import java.util.logging.Logger;

/**
 * @author czz
 * @version 1.0
 * @date 2020/10/25 10:39
 */
public class Starter {
    private static Logger logger = Logger.getLogger("ServiceStarter");
    //TODO 开启服务器
    public static void main(String[] args){
        final int port = 8088;
        IEchoServer echoServer = new EchoServerImpl(port);
        echoServer.register(IEchoServer.class, UserServiceImpl.class);
        echoServer.start();
    }
}
