package net.novaborn.pop.command;

import net.novaborn.entity.Mailbox;
import net.novaborn.pop.server.BaseCommand;
import net.novaborn.pop.server.PopSession;

import java.io.IOException;
import java.util.List;

/**
 * 查看邮件数量和总占用大小
 */
public class StatCommand extends BaseCommand {

    public StatCommand() {
        super("STAT");
    }

    @Override
    public void execute(String commandString, PopSession popSession) throws IOException {
        List<Mailbox> emailList = popSession.getEmail();
        int totalSize = 0;
        for (Mailbox email : emailList) {
            totalSize += email.getSize();
        }
        popSession.Write("+OK " + emailList.size() + " " + totalSize + "\r\n");
    }
}
