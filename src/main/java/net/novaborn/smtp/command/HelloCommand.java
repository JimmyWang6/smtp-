package net.novaborn.smtp.command;


import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import net.novaborn.entity.User;
import net.novaborn.smtp.server.*;

import java.io.IOException;

/**
 * @author Ian McFarland &lt;ian@neo.com&gt;
 * @author Jon Stevens
 * @author Jeff Schnitzer
 * @author Scott Hernandez
 */
public class HelloCommand extends BaseCommand
{
	/** */
	public HelloCommand()
	{
		super("HELO");
	}


	@Override
	public SmtpSession execute(String commandString, SmtpSession smtpSession) throws IOException {
		String[] args = this.getArgs(commandString);
		if (args.length < 2)
		{
			smtpSession.Write("501 Syntax: HELO <hostname>\r\n");
			return smtpSession;
		}
		smtpSession.setHello(args[1]);
		smtpSession.setUser(new User());
		smtpSession.setLogin(false);
		// 重置登陆
		smtpSession.Write("250 OK" +"\r\n");
		return smtpSession;
	}
}
