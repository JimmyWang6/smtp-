package net.novaborn.smtp.command;

import io.netty.channel.Channel;
import net.novaborn.smtp.server.*;


import java.io.IOException;

/**
 * @author Ian McFarland &lt;ian@neo.com&gt;
 * @author Jon Stevens
 * @author Jeff Schnitzer
 */
public class ResetCommand extends BaseCommand
{
	/** */
	public ResetCommand()
	{
		super("RSET");
	}

	/** */
	@Override
	public SmtpSession execute(String commandString, SmtpSession smtpSession) throws IOException
	{
		smtpSession.resetMailTransaction();
		smtpSession.Write("250 Ok\r\n");
		return  smtpSession;
	}
}
