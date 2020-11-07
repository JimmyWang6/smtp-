package net.novaborn.smtp.server;
import net.novaborn.smtp.command.*;
public enum CommandRegistry {
    AUTH(new AuthCommand(), true, false),
    DATA(new DataCommand(), true, true),
    EHLO(new EhloCommand(), false, false),
//    EXPN(new ExpandCommand(), true, true),
    HELO(new HelloCommand(), true, false),
    //    HELP(new HelpCommand(), true, true),
    MAIL(new MailCommand(), true, true),
    NOOP(new NoopCommand(), false, false),
    QUIT(new QuitCommand(), false, false),
    RCPT(new ReceiptCommand(), true, true),
    RSET(new ResetCommand(), true, false),
    //    STARTTLS(new StartTLSCommand(), false, false),
    VRFY(new VerifyCommand(), true, true);
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
