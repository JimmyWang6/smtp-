package net.novaborn.smtp.server;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import net.novaborn.comm.SpringContextHolder;
import net.novaborn.config.ServerConfig;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
public class SmtpServerHandler extends SimpleChannelInboundHandler<String> {
    private SmtpSession smtpSession;
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static  ServerConfig serverConfig = new ServerConfig();
    private CommandHandler commandHandler = new CommandHandler();  // 处理客户端发来的命令
    // 每有一个连接就执行这个
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        this.smtpSession=new SmtpSession(channel);
        this.smtpSession.Write("220 " + serverConfig.hostname + " ESMTP\r\n");
        channelGroup.add(channel);
        System.out.println(sdf.format(new java.util.Date())+":[客户端]" + channel.remoteAddress() + " 建立链接了----" +"当前链接数为:"+channelGroup.size());
    }


    // 发一行就执行这个方法
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws IOException {
        Channel channel = ctx.channel();
        this.smtpSession.setChannel(channel);
        System.out.println(sdf.format(new java.util.Date())+":[客户端]" + channel.remoteAddress() + "发送了:"+msg);
        this.smtpSession = commandHandler.handleCommand(this.smtpSession,msg);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(sdf.format(new java.util.Date())+":[客户端]" + channel.remoteAddress() + " 离开了");
        System.out.println("当前连接数为:" + channelGroup.size());

    }

    private void onCommand(Channel channel, String line) {
    }

    private void onData(Channel channel, String line) {
    }


    private void saveData(){
        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getBean(StringRedisTemplate.class);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        Channel ch = ctx.channel();

        ch.close();
    }
}