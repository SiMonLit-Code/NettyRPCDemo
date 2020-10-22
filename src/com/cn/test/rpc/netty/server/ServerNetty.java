package com.cn.test.rpc.netty.server;

import com.cn.test.rpc.netty.server.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-10-16 17:32:00
 * @description :
 */
public class ServerNetty {
    public ServerNetty(int port) {
        //用于监听客户端
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //用于读写
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //配置
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)//指定通道类型
                    .option(ChannelOption.SO_BACKLOG, 1024)//配置阻塞队列
                    .childHandler(new ChannelInitializer<SocketChannel>(){//配置Handler
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ServerHandler());
                        }
                    });
            ChannelFuture cf = serverBootstrap.bind(port).sync();
            System.out.println("开始监听...");
            cf.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //优雅的关闭
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
