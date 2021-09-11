package com.aronson.utils.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

/**
 * @author Sherlock
 */
public class DemoSocketServerWriteHandler1 extends ChannelInboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(NettyServer.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("InboundHandler1 进入 " + msg);
        ctx.channel().writeAndFlush("我是server。");
        logger.info("InboundHandler1 退出 " + msg);
    }
}


