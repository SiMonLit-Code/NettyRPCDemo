package com.cn.test.echorpc.client.netty;

import com.cn.test.echorpc.client.interfaces.INettyCallBack;
import com.cn.test.echorpc.utils.RPCNetty;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

/**
 * @author czz
 * @version 1.0
 * @date 2020/10/25 11:06
 *
 * RPCFactory 代理实现类
 * 传递网络连接需要的host以及port。
 * 将相关的对象，来封装实体对象传递到Netty处理类中，进行进一步的处理
 */
public class RPCFactory {
    private static Logger logger = Logger.getLogger("RPCFactory");

    public static <T> T getRemoteProxyObj(final Class<?> serviceInterface,
                                          final InetSocketAddress addr,
                                          final INettyCallBack nettyCallBack){
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class[]{serviceInterface},
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                logger.info("invoke");

                RPCNetty rpcNetty = new RPCNetty();
                rpcNetty.setClzName(serviceInterface.getName());
                rpcNetty.setArguments(args);
                rpcNetty.setMethodName(method.getName());
                rpcNetty.setParamTypes(method.getParameterTypes());

                EchoClient echoClient = new EchoClient(addr);
                echoClient.setRpcNetty(rpcNetty);
                echoClient.setiNettyCallBack(nettyCallBack);
                echoClient.start();
                return null;
            }
        });

    }

}
