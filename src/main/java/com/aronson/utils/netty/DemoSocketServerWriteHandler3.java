package com.aronson.utils.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.apache.log4j.Logger;

/**
 * @author Sherlock
 */
public class DemoSocketServerWriteHandler3 extends ChannelOutboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(NettyServer.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        logger.info("OutboundHandler3 进入 " + msg);
        ctx.write(msg + "3", promise);
        logger.info("OutboundHandler3 退出 " + msg);
    }
}


