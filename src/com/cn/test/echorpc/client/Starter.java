package com.cn.test.echorpc.client;

import com.cn.test.echorpc.client.interfaces.INettyCallBack;
import com.cn.test.echorpc.client.netty.RPCFactory;
import com.cn.test.echorpc.service.netty.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.InetSocketAddress;
import java.util.logging.Logger;

/**
 * @author czz
 * @version 1.0
 * @date 2020/10/25 11:05
 */
public class Starter {
    private static Logger logger = Logger.getLogger("ClientStarter");
    public static void main(String[] args) {
        final String host = "127.0.0.1";
        final int port = 8088;
        InetSocketAddress address = new InetSocketAddress(host,port);
        //TODO 开启客户端
        IUserService userService = RPCFactory.getRemoteProxyObj(IUserService.class, address, new INettyCallBack() {
            @Override
            public Object callBack(Object obj) {
                try{
                    logger.info("obj>"+new ObjectMapper().writeValueAsString(obj));
                }catch (Exception e){
                    logger.info("Exception >>>"+e.getMessage());
                    e.printStackTrace();
                }
                return null;
            }
        });
        userService.getUser("1");
    }
}
