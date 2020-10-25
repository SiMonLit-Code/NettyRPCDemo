package com.cn.test.echorpc.client.handler;

import com.cn.test.echorpc.client.interfaces.INettyCallBack;
import com.cn.test.echorpc.utils.RPCNetty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.logging.Logger;

/**
 * @author czz
 * @version 1.0
 * @date 2020/10/25 11:36
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static Logger logger = Logger.getLogger("EchoClientHandler");

    private RPCNetty rpcNetty;
    private INettyCallBack nettyCallBack;

    public EchoClientHandler() {
    }

    public EchoClientHandler(RPCNetty rpcNetty, INettyCallBack nettyCallBack) {
        this.rpcNetty = rpcNetty;
        this.nettyCallBack = nettyCallBack;
    }


    /**
     * 请求数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String sendMsg = objectMapper.writeValueAsString(rpcNetty);
        logger.info("client -- channelActive >>>"+ sendMsg);
        ctx.writeAndFlush(Unpooled.copiedBuffer(sendMsg,CharsetUtil.UTF_8));
    }

    /**
     * 服务器返回数据
     * @param channelHandlerContext
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        String reMsg = byteBuf.toString(CharsetUtil.UTF_8);
        logger.info("client --- channelRead0 >>>"+reMsg);
        nettyCallBack.callBack(reMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("Client Exception >>>"+cause.getMessage());
        ctx.close();
    }

    public RPCNetty getRpcNetty() {
        return rpcNetty;
    }

    public void setRpcNetty(RPCNetty rpcNetty) {
        this.rpcNetty = rpcNetty;
    }

    public INettyCallBack getNettyCallBack() {
        return nettyCallBack;
    }

    public void setNettyCallBack(INettyCallBack nettyCallBack) {
        this.nettyCallBack = nettyCallBack;
    }
}
