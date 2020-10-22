package com.cn.test.rpc.netty.client;

import com.cn.test.rpc.netty.client.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-10-22 09:50:00
 * @description :
 */
public class ClientNetty {
    public ClientNetty(String host, int port) {
        EventLoopGroup el = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(el)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY , true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture cf = bootstrap.connect(host, port).sync();
            System.out.println("开始连接服务器");
            cf.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            el.shutdownGracefully();
        }
    }
}

