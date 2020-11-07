package net.novaborn.pop.command;

import net.novaborn.entity.Mailbox;
import net.novaborn.pop.server.BaseCommand;
import net.novaborn.pop.server.PopSession;

import java.io.IOException;
import java.util.List;

/**
 * 获取某封信件的内容
 */
public class RetrCommand extends BaseCommand {

    public RetrCommand() {
        super("RETR");
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
                    popSession.Write("-Err Message not exists\r\n");
                } else {
                    // 用户输入1，其实是查看emaillist里第0个信件
                    Mailbox email = emailList.get(index - 1);
                    popSession.Write("+OK " + email.getSize() + "\r\n");
                    if (email.getData() != null) {
                        popSession.Write(email.getData());
                    }
                    popSession.Write("\r\n.\r\n");
                }
            } catch (NumberFormatException e) {
                popSession.Write("-Err The parameter must be a positive integer\r\n");
            }
        }
    }
}
