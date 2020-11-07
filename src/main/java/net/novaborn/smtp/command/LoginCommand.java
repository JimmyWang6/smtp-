package net.novaborn.smtp.command;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import net.novaborn.smtp.server.*;

import java.io.IOException;

//public class LoginCommand extends BaseCommand {
//    public static final String VERB = "AUTH";
//
//    /** Creates a new instance of AuthCommand */
//    public LoginCommand()
//    {
//        super("LOGI");
//    }
//    /** */
//    @Override
//    public String execute(String preState, String commandString, Channel channel, ServerConfig serverConfig, CommandHandler commandHandler)
//            throws IOException
//    {
//        String username = commandString;
//        System.out.println("username="+username);
//        return SmtpState.PASSWORD.toString();
//    }
//
//}
