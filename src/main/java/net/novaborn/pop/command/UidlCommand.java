package net.novaborn.pop.command;

import net.novaborn.entity.Mailbox;
import net.novaborn.pop.server.BaseCommand;
import net.novaborn.pop.server.PopSession;

import java.io.IOException;
import java.util.List;

/**
 * 查询某一信件的唯一标识符，如果缺省参数，则展示所有的信件
 */
public class UidlCommand extends BaseCommand {

    public UidlCommand() {
        super("UIDL");
    }

    @Override
    public void execute(String commandString, PopSession popSession) throws IOException {
        String[] args = this.getArgs(commandString);
        List<Mailbox> emailList = popSession.getEmail();
        if (args.length < 2) {
            popSession.Write("+OK\r\n");
            for (int i = 0; i < emailList.size(); i++) {
                popSession.Write(i + 1 + " " + emailList.get(i).getUid() + "\r\n");
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
                    popSession.Write("+OK " + index + " " + emailList.get(index).getUid() + "\r\n");
                }
            } catch (NumberFormatException e) {
                popSession.Write("-Err the parameter must be a positive integer\r\n");
            }
        }
    }
}
