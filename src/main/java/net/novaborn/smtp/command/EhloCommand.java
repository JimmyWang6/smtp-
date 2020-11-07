package net.novaborn.smtp.command;

import net.novaborn.smtp.server.BaseCommand;
import net.novaborn.config.ServerConfig;
import net.novaborn.smtp.server.SmtpSession;

import java.io.IOException;
public class EhloCommand extends BaseCommand
{
	/** */
	public EhloCommand()
	{
		super("EHLO");
	}

	/** */
	@Override
	public SmtpSession execute(String commandString, SmtpSession smtpSession) throws IOException
	{
		String[] args = this.getArgs(commandString);
		if (args.length < 2)
		{
			smtpSession.Write("501 Syntax: EHLO hostname\r\n");
			return smtpSession;
		}

		smtpSession.resetMailTransaction();
		smtpSession.setHello(args[1]);

//		postfix returns...
//		250-server.host.name
//		250-PIPELINING
//		250-SIZE 10240000
//		250-ETRN
//		250 8BITMIME
		StringBuilder response = new StringBuilder();
		response.append("250-");
		response.append(ServerConfig.hostname);
		response.append("\r\n" + "250-8BITMIME");
		int maxSize = ServerConfig.MaxMessageSize;
		if (maxSize > 0)
		{
			response.append("\r\n" + "250-SIZE ");
			response.append(maxSize);
		}
		// Enabling / Hiding TLS is a server setting
//		if (sess.getServer().getEnableTLS() && !sess.getServer().getHideTLS())
//		{
//			response.append("\r\n" + "250-STARTTLS");
//		}

		// Check to see if we support authentication
//		AuthenticationHandlerFactory authFact = sess.getServer().getAuthenticationHandlerFactory();
//		if (authFact != null)
//		{
//			List<String> supportedMechanisms = authFact.getAuthenticationMechanisms();
//			if (!supportedMechanisms.isEmpty())
//			{
//				response.append("\r\n" + "250-" + AuthCommand.VERB + " ");
//				response.append(TextUtils.joinTogether(supportedMechanisms, " "));
//			}
//		}
        response.append("\r\n" + "250-AUTH LOGIN PLAIN");
		response.append("\r\n" + "250 Ok\r\n");
		smtpSession.Write(response.toString());
		return smtpSession;
	}
}
