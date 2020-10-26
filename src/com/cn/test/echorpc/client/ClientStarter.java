package com.cn.test.echorpc.client;

import com.cn.test.echorpc.client.interfaces.INettyCallBack;
import com.cn.test.echorpc.client.netty.RPCProxyFactory;
import com.cn.test.echorpc.service.netty.IUserService;
import com.cn.test.fib.FibRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.InetSocketAddress;
import java.util.logging.Logger;

/**
 * @author czz
 * @version 1.0
 * @date 2020/10/25 11:05
 */
public class ClientStarter {
    private static Logger logger = Logger.getLogger("ClientStarter");
    public static void main(String[] args) {
        final String host = "127.0.0.1";
        final int port = 8088;
        InetSocketAddress address = new InetSocketAddress(host,port);
        //TODO 开启客户端
        IUserService userService = RPCProxyFactory.getRemoteProxyObj(IUserService.class, address, obj -> {
            try{
                String returnMsg = new ObjectMapper().writeValueAsString(obj);
                logger.info("obj>>"+returnMsg);
                System.out.println(returnMsg);
            }catch (Exception e){
                logger.info("Exception >>>"+e.getMessage());
                e.printStackTrace();
            }
            return null;
        });
        FibRequest fibRequest = RPCProxyFactory.getRemoteProxyObj(FibRequest.class, address, obj -> {
            try{
                String returnMsg = new ObjectMapper().writeValueAsString(obj);
                logger.info("obj>>"+returnMsg);
                System.out.println(returnMsg);
            }catch (Exception e){
                logger.info("Exception >>>"+e.getMessage());
                e.printStackTrace();
            }
            return null;
        });

        //远程调用方法
        userService.getUser("1");
        fibRequest.handle(10);
    }
}
