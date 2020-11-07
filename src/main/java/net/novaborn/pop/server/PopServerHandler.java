package net.novaborn.pop.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import net.novaborn.config.ServerConfig;

import java.text.SimpleDateFormat;

public class PopServerHandler extends SimpleChannelInboundHandler<String> {

    private PopSession popSession;
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private CommandHandler commandHandler = new CommandHandler();  // 处理客户端发来的命令

    // 每有一个连接就执行这个函数
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        this.popSession =new PopSession(channel);
        this.popSession.Write("+OK " + ServerConfig.hostname + " POP3Server\r\n");
        channelGroup.add(channel);
        System.out.println(sdf.format(new java.util.Date())+":[客户端]" + channel.remoteAddress() + " 建立链接了----" +"当前链接数为:"+channelGroup.size());
    }

    // 每有一行读入就执行这个函数
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        this.popSession.setChannel(channel);
        System.out.println(sdf.format(new java.util.Date())+":[客户端]" + channel.remoteAddress() + "发送了:"+msg);
        commandHandler.handleCommand(this.popSession,msg);  // 处理命令
    }

    // 每有一个断开连接就执行这个函数
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(sdf.format(new java.util.Date())+":[客户端]" + channel.remoteAddress() + " 离开了");
        System.out.println("当前连接数为:" + channelGroup.size());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        Channel ch = ctx.channel();
        ch.close();
    }
}
