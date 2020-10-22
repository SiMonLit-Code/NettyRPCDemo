package com.cn.test.rpc.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-10-22 10:03:00
 * @description :
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "Hi! I am a client "+Thread.currentThread().getName();
        byte[] bytes = msg.getBytes("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer(bytes);
        ctx.writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        System.out.print("Receive a new Message from server:");
        System.out.println(new String(bytes,"UTF-8"));
    }

   /* @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        String msg = "Receive a message already";
        ctx.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes("UTF-8")));
    }*/

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Have a exception:"+cause.getMessage());
        ctx.close();
    }
}
