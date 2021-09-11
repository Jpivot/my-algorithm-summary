package com.aronson.utils.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.apache.log4j.Logger;

/**
 * @author Sherlock
 */
public class DemoSocketServerWriteHandler2 extends ChannelOutboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(NettyServer.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        logger.info("OutboundHandler2 进入 " + msg);
        ctx.write(msg + "2", promise);
        logger.info("OutboundHandler2 退出 " + msg);
    }
}


