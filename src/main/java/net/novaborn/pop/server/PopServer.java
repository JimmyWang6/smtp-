package net.novaborn.pop.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.StringTokenizer;

@Slf4j
public class PopServer {
    public static boolean SEVER_STATUS = false;
    private static EventLoopGroup bossGroup;
    private static EventLoopGroup workerGroup;

    public static void run() throws Exception {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();

        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new PopServerInitializer());
        ChannelFuture f = b.bind(110).sync();

        f.addListener((ChannelFutureListener) channelFuture -> {
//                log.info("---pop邮件服务器 启动了---");
            SEVER_STATUS = true;
        });
        f.channel().closeFuture().sync();
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

    public static void main(String[] args) {
        startupServer();
//        String content = "PG1ldGEgaHR0\r\ncC1lcXVpdj0iQ29udGVudC1UeX\r\nBlIiBjb250ZW50\r\nPSJ0ZXh0L2h0bWw7IGNoYXJzZ\r\n\r\nXQ9R0\r\nIxODA\r\nzMCI\r\n+PGRpdj7\r\nV4srH0r\r\nu0zrLiy\r\ntQ8L2\r\nRpdj4=\r\n";
//        StringTokenizer stringTokenizer = new StringTokenizer(content, "\n", true);
//        while (stringTokenizer.hasMoreTokens()) {
//            String line = stringTokenizer.nextToken();
//            System.out.println(line);
//            System.out.println(line.length());
//            if (line.equals("\r")) {
//                System.out.println("出来了");
//                break;
//            }
//        }
    }
}
