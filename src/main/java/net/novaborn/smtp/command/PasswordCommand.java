//package net.novaborn.smtp.command;
//
//import io.netty.channel.Channel;
//import net.novaborn.smtp.server.BaseCommand;
//import net.novaborn.config.ServerConfig;
//import net.novaborn.smtp.server.SmtpState;
//
//import java.io.IOException;
//
//public class PasswordCommand extends BaseCommand {
//    public static final String VERB = "AUTH";
//
//    /** Creates a new instance of AuthCommand */
//    public PasswordCommand()
//    {
//        super("PASS");
//    }
//    /** */
//    @Override
//    public String execute(String preState, String commandString, Channel channel, ServerConfig serverConfig)
//            throws IOException
//    {
//        String password = commandString;
//        System.out.println("username="+username);
//        return SmtpState.PASSWORD.toString();
//    }
//}
