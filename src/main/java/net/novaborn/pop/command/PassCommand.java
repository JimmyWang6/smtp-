package net.novaborn.pop.command;

import net.novaborn.comm.SpringContextHolder;
import net.novaborn.dao.MailboxMapper;
import net.novaborn.dao.UserMapper;
import net.novaborn.entity.Mailbox;
import net.novaborn.entity.MailboxExample;
import net.novaborn.entity.User;
import net.novaborn.pop.server.BaseCommand;
import net.novaborn.pop.server.PopSession;

import java.io.IOException;
import java.util.List;

/**
 * 输入密码并登陆，拿到用户的所有邮件
 */
public class PassCommand extends BaseCommand {

    public PassCommand() {
        super("PASS");
    }

    @Override
    public void execute(String commandString, PopSession popSession) throws IOException {
        String[] args = this.getArgs(commandString);
        if (args.length < 2) {
            popSession.Write("-Err AUTH mechanism mismatch\r\n");
        } else if (popSession.getUser().getUserName() == null) {    // 如果没有用户名则提示
            popSession.Write("-Err USER command first\r\n");
        } else {
            String password = args[1];

            // 开始登陆
            /////////////////////////////////
            UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
            User user = userMapper.selectByUserName(popSession.getUser().getUserName());
            //TODO 测试用
            /////////////////////////////////
            if (user.getPassword().equals(password)) {
                // 登陆成功
                popSession.setLogin(true);
                // 通过用户名获取当前用户的信件
                ////////////////////////////
                MailboxMapper emailMapper = SpringContextHolder.getBean(MailboxMapper.class);
                MailboxExample mailboxExample = new MailboxExample();
                mailboxExample.createCriteria().andToEqualTo(user.getUserName());
                List<Mailbox> emailList = emailMapper.selectByExampleWithBLOBs(mailboxExample);
                //TODO 测试用
                ////////////////////////////
                if (popSession.setEmail(emailList)) {
                    popSession.Write("+OK\r\n");
                } else {
                    popSession.Write("-Err get emails failed\r\n");
                }
            } else {
                popSession.Write("-Err authentication failed\r\n");
            }
        }
    }
}
