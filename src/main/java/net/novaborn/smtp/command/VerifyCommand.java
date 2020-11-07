package net.novaborn.smtp.command;
import net.novaborn.smtp.server.BaseCommand;
import net.novaborn.smtp.server.SmtpSession;

import java.io.IOException;

/**
 * @author Ian McFarland &lt;ian@neo.com&gt;
 * @author Jon Stevens
 */
public class VerifyCommand extends BaseCommand
{
	/** */
	public VerifyCommand()
	{
		super("VRFY");
	}

	/** */
//	@Override
//	public String execute(String preState, String commandString, Channel channel, ServerConfig serverConfig, CommandHandler commandHandler) throws IOException
//	{
//		channel.writeAndFlush("502 VRFY command is disabled\r\n");
//		return preState;
//	}

	@Override
	public SmtpSession execute(String commandString, SmtpSession smtpSession) throws IOException {
		smtpSession.Write("502 VRFY command is disabled\r\n");
		return smtpSession;
	}
}
