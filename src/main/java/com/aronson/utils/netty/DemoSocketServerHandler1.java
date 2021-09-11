package com.aronson.utils.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

/**
 * @author Sherlock
 */
public class DemoSocketServerHandler1 extends ChannelInboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(NettyServer.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("InboundHandler2 进入 " + msg);
        ctx.fireChannelRead(msg);
        logger.info("InboundHandler2 退出 " + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
