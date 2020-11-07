package net.novaborn.pop.command;

import net.novaborn.pop.server.BaseCommand;
import net.novaborn.pop.server.PopSession;

import java.io.IOException;

public class NoopCommand extends BaseCommand {

    public NoopCommand() {
        super("NOOP");
    }

    @Override
    public void execute(String commandString, PopSession smtpSession) throws IOException {
        smtpSession.Write("+OK\r\n");
    }
}
