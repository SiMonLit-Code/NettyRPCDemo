package com.cn.test.echorpc.service;

import com.cn.test.echorpc.service.netty.IUserService;
import com.cn.test.echorpc.service.netty.UserServiceImpl;
import com.cn.test.echorpc.service.echoServer.EchoServerImpl;
import com.cn.test.echorpc.service.echoServer.IEchoServer;
import com.cn.test.fib.FibRequest;
import com.cn.test.fib.FibRequestHandle;

import java.util.logging.Logger;

/**
 * @author czz
 * @version 1.0
 * @date 2020/10/25 10:39
 */
public class ServiceStarter {
    private static Logger logger = Logger.getLogger("ServiceStarter");
    //TODO 开启服务器
    public static void main(String[] args){
        final int port = 8088;
        IEchoServer echoServer = new EchoServerImpl(port);
        //注册被调用的类
        echoServer.register(IUserService.class, UserServiceImpl.class);
        echoServer.register(FibRequest.class, FibRequestHandle.class);
        //开启服务
        echoServer.start();
    }
}
