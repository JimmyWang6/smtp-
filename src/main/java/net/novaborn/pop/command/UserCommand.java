package net.novaborn.pop.command;


import net.novaborn.pop.server.BaseCommand;
import net.novaborn.pop.server.PopSession;

import java.io.IOException;

/**
 * 输入用户名
 */
public class UserCommand extends BaseCommand {

    public UserCommand() {
        super("USER");
    }

    @Override
    public void execute(String commandString, PopSession popSession)
            throws IOException {
        String[] args = this.getArgs(commandString);
        if (args.length < 2) {
            popSession.Write("-Err AUTH mechanism mismatch\r\n");
        } else {
            popSession.getUser().setUserName(args[1]);
            popSession.Write("+OK\r\n");
        }
    }

}

