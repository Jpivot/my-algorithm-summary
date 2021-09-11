package com.aronson.utils.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

import java.util.UUID;

/**
 * @author Sherlock
 */
public class DemoSocketServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(NettyServer.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("InboundHandler1 进入 " + msg);
        logger.info("Client Address ====== {0}"+ctx.channel().remoteAddress());
        logger.info(msg);
        ctx.channel().writeAndFlush("from server:" + UUID.randomUUID());
        // 进行调用链的传递
        ctx.fireChannelRead(msg);
//        TimeUnit.MILLISECONDS.sleep(500);
        logger.info("InboundHandler1 退出 " + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
