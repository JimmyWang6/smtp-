package net.novaborn.smtp.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmtpServer {
    public static boolean SEVER_STATUS = false;
    private static EventLoopGroup bossGroup;
    private static EventLoopGroup workerGroup;

    public static void run() throws Exception {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();

            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, Integer.valueOf(1024))
                    .childHandler(new SmtpServerInitializer());
            ChannelFuture f = b.bind(25).sync();

            f.addListener((ChannelFutureListener) channelFuture -> {
//                log.info("---邮件服务器 启动了---");
                SEVER_STATUS = true;
            });
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
//            log.info("---SmtpServer 关闭了---");
        }
    }

    public static void main(String[] args) {
        startupServer();
    }

    public static void startupServer() {
        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdownServer() {
        if ((bossGroup != null) && (!bossGroup.isShutdown())) {
            bossGroup.shutdownGracefully();
        }
        if ((workerGroup != null) && (!workerGroup.isShutdown())) {
            workerGroup.shutdownGracefully();
        }
    }
}