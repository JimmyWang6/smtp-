package net.novaborn.pop.command;

import net.novaborn.pop.server.BaseCommand;
import net.novaborn.pop.server.PopSession;

import java.io.IOException;

/**
 * 重置邮箱，清除所有邮件的删除标记
 */
public class RsetCommand extends BaseCommand {

    public RsetCommand() {
        super("RSET");
    }

    @Override
    public void execute(String commandString, PopSession popSession) throws IOException {
        popSession.getEmailsToDelete().clear();
        popSession.Write("+OK\r\n");
    }
}
