package com.cn.test.rpc.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-10-16 17:48:00
 * @description :
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 检测通道有数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)  {
        //将数据对象转换类型
        ByteBuf buf = (ByteBuf)msg;
        //创建数据长度大小的字节数组
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
//        System.out.println("接收到的消息:"+new String(bytes,"UTF-8"));



        //异步处理耗时任务
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5*1000);
                    System.out.println("接收到的消息:"+new String(bytes,"UTF-8"));
                    System.out.println("耗时5秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    /**
     * 读完成
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        //读完信息后反馈
        String msg = "服务器已接受到消息";
        ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes("UTF-8"));
        ctx.writeAndFlush(buf);
        ctx.flush();
    }



    /**
     * 有异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Have a exception"+cause.getMessage());
        ctx.close();
    }
}
