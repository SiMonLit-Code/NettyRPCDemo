package com.cn.test.echorpc.service.echoServer;

import com.cn.test.echorpc.service.handler.EchoServerHandler;
import com.cn.test.echorpc.utils.StaticData;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.logging.Logger;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-10-22 13:57:00
 * @description :
 */
public class EchoServerImpl implements IEchoServer{
    private final Integer port ;
    private static Boolean running = false;
    private static Logger logger = Logger.getLogger("EchoServerImpl");

    public EchoServerImpl(Integer port) {
        this.port = port;
    }

    @Override
    public void start() {
        logger.info("start");
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    //指定NIO的传输Channel
                    .channel(NioServerSocketChannel.class)
                    //设置socket地址使用的端口
                    .localAddress(new InetSocketAddress(port))
                    //添加EchoServerHandler到Channel的channelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //绑定服务器; sync等待服务器关闭
            ChannelFuture cf = serverBootstrap.bind().sync();
            running = true;
            //关闭channel
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭EventLoopGroup，释放所有资源
            group.shutdownGracefully();
            running = false;
        }

    }

    @Override
    public void stop() {
        running=false;
    }

    @Override
    public void register(Class serverInterface, Class impl) {
        StaticData.serviceRegistry.put(serverInterface.getName(), impl);
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
