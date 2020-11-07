package net.novaborn.pop.server;

import net.novaborn.pop.command.*;

public enum CommandRegistry {
    AUTH(new UserCommand(), true, false),
    DELE(new DeleCommand(), true, true),
//    DATA(new DataCommand(), true, true),
//    EHLO(new EhloCommand(), false, false),
////    EXPN(new ExpandCommand(), true, true),
//    HELO(new HelloCommand(), true, false),
//    //    HELP(new HelpCommand(), true, true),
    LIST(new ListCommand(), true, true),
//    MAIL(new MailCommand(), true, true),
    NOOP(new NoopCommand(), false, false),
    PASS(new PassCommand(), true, false),
    QUIT(new QuitCommand(), false, false),
    RETR(new RetrCommand(), true, true),
    RSET(new RsetCommand(), true, true),
    STAT(new StatCommand(), true, true),
//    //    STARTTLS(new StartTLSCommand(), false, false),
    TOP(new TopCommand(), true, true),
    UIDL(new UidlCommand(), true, true);
//    VRFY(new VerifyCommand(), true, true);
    private Command command;

    /**
     *
     */
    CommandRegistry(Command cmd, boolean checkForStartedTLSWhenRequired, boolean checkForAuthIfRequired) {
        // 查看是否需要TLS安全传输
//        if (checkForStartedTLSWhenRequired)
//            this.command = new RequireTLSCommandWrapper(cmd);
//        else
//            this.command = cmd;
        this.command = cmd;
        // 查看是否需要先登陆才能操作
        if (checkForAuthIfRequired)
            this.command = new RequireAuthCommandWrapper(this.command);
    }

    /**
     *
     */
    public Command getCommand() {
        return this.command;
    }
}
