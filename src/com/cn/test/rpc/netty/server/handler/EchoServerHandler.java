package com.cn.test.rpc.netty.server.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.logging.Logger;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-10-22 14:16:00
 * @description :
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = Logger.getLogger("EchoServerHandler");

    /**
     * channelRead方法：接受到客户端消息后，会进入此方法。
     * 接受到客户端消息后，将字节转为字符串，并且，对字符串进行json解析，得到实体模型，其中包含了类，方法，参数类型，参数值。
     * 再使用java中的invoke，可以直接调用方法，并且获取返回值。
     * 获取返回值后，转为ByteBuf类型，再写入流中，返回到客户端。
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("channelRead");
        ByteBuf byteBuf = (ByteBuf)msg;
        try{
            String read = byteBuf.toString(CharsetUtil.UTF_8);
            JSONObject jsonObject = new JSONObject();
            jsonObject.
        }

    }

}
