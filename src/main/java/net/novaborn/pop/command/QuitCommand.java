package net.novaborn.pop.command;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import net.novaborn.comm.SpringContextHolder;
import net.novaborn.dao.MailboxMapper;
import net.novaborn.pop.server.BaseCommand;
import net.novaborn.pop.server.PopSession;

import java.io.IOException;
import java.util.Vector;

/**
 * 退出连接，如果有标记删除的邮件，在退出登陆前删除所有标记删除的邮件
 */
public class QuitCommand extends BaseCommand {

    public QuitCommand() {
        super("QUIT");
    }

    @Override
    public void execute(String commandString, PopSession popSession) throws IOException {
        Vector<Integer> emailsToDelete = popSession.getEmailsToDelete();
        for (int emailId: emailsToDelete) {
            ///////////////////////////////////
            MailboxMapper emailMapper = SpringContextHolder.getBean(MailboxMapper.class);
            emailMapper.deleteByPrimaryKey(emailId);
            //TODO 测试用
            System.out.println("删除的邮件ID为：" + emailId);
            ///////////////////////////////////
        }
        ChannelFuture future = popSession.getChannel().writeAndFlush("+OK Bye\r\n");
        future.addListener(ChannelFutureListener.CLOSE);
    }
}
