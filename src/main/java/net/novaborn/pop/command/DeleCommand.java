package net.novaborn.pop.command;

import net.novaborn.entity.Mailbox;
import net.novaborn.pop.server.BaseCommand;
import net.novaborn.pop.server.PopSession;

import java.io.IOException;
import java.util.List;

/**
 * 给邮件添加删除标记，当退出时就会将标记的邮件删除，可以通过RSET
 */
public class DeleCommand extends BaseCommand {

    public DeleCommand() {
        super("DELE");
    }

    @Override
    public void execute(String commandString, PopSession popSession) throws IOException {
        String[] args = this.getArgs(commandString);
        List<Mailbox> emailList = popSession.getEmail();
        if (args.length < 2) {
            popSession.Write("-Err bad syntax\r\n");
        } else {
            try {
                int index = Integer.parseInt(args[1]);
                if (index <= 0) {
                    popSession.Write("-Err The parameter must be a positive integer\r\n");
                } else if (index > emailList.size()) {
                    popSession.Write("-Err Message not exists");
                } else {
                    // 用户输入1，其实是删除emaillist里第0个信件
                    Mailbox email = popSession.getEmail().get(index - 1);
                    // 将待删邮件的ID添加到待删列表里
                    popSession.getEmailsToDelete().add(email.getUid());
                    popSession.Write("+OK Message will be delete when you quit\r\n");
                }
            } catch (NumberFormatException e) {
                popSession.Write("-Err The parameter must be a positive integer\r\n");
            }
        }
    }
}
