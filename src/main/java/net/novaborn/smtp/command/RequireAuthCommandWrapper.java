package net.novaborn.smtp.command;
import net.novaborn.smtp.server.Command;
import net.novaborn.smtp.server.SmtpSession;

import java.io.IOException;

public class RequireAuthCommandWrapper implements Command
{

    private Command wrapped;

    /**
     * @param wrapped the wrapped command (not null)
     */
    public RequireAuthCommandWrapper(Command wrapped)
    {
        this.wrapped = wrapped;
    }

    /**
     * {@inheritDoc}
     */
//    public String execute(String preState, String commandString, Channel channel, ServerConfig serverConfig, CommandHandler commandHandler)
//            throws IOException
//    {
//        String isLogin =  (String)channel.attr(AttributeKey.valueOf("isLogin")).get();
//        if (!(serverConfig.isAuthenticated=="true") || isLogin=="true")
//            return wrapped.execute(preState,commandString, channel,serverConfig,commandHandler);
//        else
//            channel.writeAndFlush("530 5.7.0  Authentication required\r\n");
//            return preState;
//    }

    @Override
    public SmtpSession execute(String commandString, SmtpSession smtpSession) throws IOException {
        if(smtpSession.isLogin()||!(smtpSession.getServerConfig().isAuthenticated)){
            return wrapped.execute(commandString,smtpSession);
        }
        else{
            smtpSession.Write("530 5.7.0  Authentication required\r\n");
            return smtpSession;
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getName()
    {
        return wrapped.getName();
    }
}
