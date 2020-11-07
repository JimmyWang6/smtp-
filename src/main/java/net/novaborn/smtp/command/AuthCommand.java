package net.novaborn.smtp.command;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import net.novaborn.smtp.server.*;

import java.io.IOException;
import java.util.Locale;

//package net.novaborn.smtp.command;//package command;
//
//
//import io.netty.channel.Channel;
//import io.netty.util.AttributeKey;
//import net.novaborn.smtp.server.BaseCommand;
//import net.novaborn.config.ServerConfig;
//
//import java.io.IOException;
//import java.util.Locale;
//
public class AuthCommand extends BaseCommand
{

	public static final String VERB = "AUTH";

	/** Creates a new instance of AuthCommand */
	public AuthCommand()
	{
		super("AUTH");
	}
	/** */
	@Override
	public SmtpSession execute(String commandString,SmtpSession smtpSession)
			throws IOException
	{
        String[] args = this.getArgs(commandString);
		if(args.length<2){
			smtpSession.Write("502 Syntax\r\n");
			return smtpSession;
		}
        if (args[1].toUpperCase(Locale.ENGLISH).equals("LOGIN"))
        {
            smtpSession.Write("334 VXNlcm5hbWU6\r\n");
            smtpSession.setLoginProcessCount(smtpSession.getLoginProcessCount()+1);
        }
        else {
			smtpSession.Write("504 AUTH mechanism mismatch\r\n");
		}
        return smtpSession;

	}

}

