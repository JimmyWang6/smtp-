package net.novaborn.pop.command;

import net.novaborn.entity.Mailbox;
import net.novaborn.pop.server.BaseCommand;
import net.novaborn.pop.server.PopSession;

import java.io.IOException;
import java.util.List;

/**
 * 将某一信件的占用空间展示出来，如果缺省参数，则列出所有信件
 */
public class ListCommand extends BaseCommand {

    public ListCommand() {
        super("LIST");
    }

    @Override
    public void execute(String commandString, PopSession popSession) throws IOException {
        String[] args = this.getArgs(commandString);
        List<Mailbox> emailList = popSession.getEmail();
        if (args.length < 2) {
            popSession.Write("+OK\r\n");
            for (int i = 0; i < emailList.size(); i++) {
                popSession.Write(i + 1 + " " + emailList.get(i).getSize() + "\r\n");
            }
            popSession.Write(".\r\n");
        } else {
            try {
                int index = Integer.parseInt(args[1]);
                if (index <= 0) {
                    popSession.Write("-Err the parameter must be a positive integer\r\n");
                } else if (index > emailList.size()) {
                    popSession.Write("-Err Message not exists\r\n");
                } else {
                    // 用户输入1，其实是查看emaillist里第0个信件
                    index--;
                    popSession.Write("+OK " + index + " " + emailList.get(index).getSize() + "\r\n");
                }
            } catch (NumberFormatException e) {
                popSession.Write("-Err the parameter must be a positive integer\r\n");
            }
        }
    }
}
