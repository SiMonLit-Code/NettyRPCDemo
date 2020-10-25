package com.cn.test.echorpc.client.netty;

import com.cn.test.echorpc.client.handler.EchoClientHandler;
import com.cn.test.echorpc.client.interfaces.INettyCallBack;
import com.cn.test.echorpc.utils.RPCNetty;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.logging.Logger;

/**
 * @author czz
 * @version 1.0
 * @date 2020/10/25 11:22
 */
public class EchoClient {
    private static Logger logger = Logger.getLogger("EchoClient");

    private String host;
    private int port;
    private InetSocketAddress addr;
    private INettyCallBack iNettyCallBack;
    private RPCNetty rpcNetty;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public EchoClient(InetSocketAddress addr) {
        this.addr = addr;
    }

    public void start(){
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    EchoClientHandler echoClientHandler = new EchoClientHandler();
                    echoClientHandler.setNettyCallBack(iNettyCallBack);
                    echoClientHandler.setRpcNetty(rpcNetty);
                    socketChannel.pipeline().addLast(echoClientHandler);
                }
            });
            ChannelFuture cf = b.connect(addr).sync();
            cf.channel().closeFuture().sync();
        }catch (Exception e){
            logger.info("Exception>>>"+e.getMessage());
        }finally {
            group.shutdownGracefully();
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        EchoClient.logger = logger;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetSocketAddress getAddr() {
        return addr;
    }

    public void setAddr(InetSocketAddress addr) {
        this.addr = addr;
    }

    public INettyCallBack getiNettyCallBack() {
        return iNettyCallBack;
    }

    public void setiNettyCallBack(INettyCallBack iNettyCallBack) {
        this.iNettyCallBack = iNettyCallBack;
    }

    public RPCNetty getRpcNetty() {
        return rpcNetty;
    }

    public void setRpcNetty(RPCNetty rpcNetty) {
        this.rpcNetty = rpcNetty;
    }
}
