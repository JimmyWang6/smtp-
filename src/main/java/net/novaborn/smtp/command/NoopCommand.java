package net.novaborn.smtp.command;


import net.novaborn.smtp.server.BaseCommand;
import net.novaborn.smtp.server.SmtpSession;

import java.io.IOException;

/**
 * @author Ian McFarland &lt;ian@neo.com&gt;
 * @author Jon Stevens
 * @author Jeff Schnitzer
 */
public class NoopCommand extends BaseCommand
{
	/** */
	public NoopCommand()
	{
		super("NOOP");
	}

	/** */

	@Override
	public SmtpSession execute(String commandString, SmtpSession smtpSession) throws IOException {
		smtpSession.Write("250 Ok\r\n");
		return smtpSession;
	}
}
