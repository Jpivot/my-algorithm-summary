package com.aronson.utils.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.log4j.Logger;

/**
 * @author Sherlock
 */
public class NettyServer {
    private static final Logger logger = Logger.getLogger(NettyServer.class);

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            // 实例化启动配置类
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 给引导类配置两大线程组,确定了线程模型
            bootstrap.group(parentGroup, childGroup)
                    // 指定 IO 模型
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            // 消息的业务处理逻辑
                            pipeline.addLast(new DemoSocketServerWriteHandler1());
                            pipeline.addLast(new DemoSocketServerWriteHandler2());
                            pipeline.addLast(new DemoSocketServerWriteHandler3());
                            pipeline.addLast(new DemoSocketServerWriteHandler4());
                        }
                    });

            ChannelFuture future = bootstrap.bind(8888).sync();
            logger.info("服务器已启动。。。");

            future.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
