package net.novaborn.smtp.command;//package command;
import net.novaborn.config.ServerConfig;
import net.novaborn.entity.EmailModel;
import net.novaborn.smtp.server.*;
import net.novaborn.util.EmailUtils;

import java.io.IOException;
import java.util.Locale;

/**
 * @author Ian McFarland &lt;ian@neo.com&gt;
 * @author Jon Stevens
 * @author Scott Hernandez
 * @author Jeff Schnitzer
 */
public class MailCommand extends BaseCommand
{
	/** */
	public MailCommand()
	{
		super("MAIL");
	}

	/** */
	/* (non-Javadoc)
	 * @see org.subethamail.smtp.server.BaseCommand#execute(java.lang.String, org.subethamail.smtp.server.Session)
	 */
	@Override
	public SmtpSession execute( String commandString, SmtpSession smtpSession) throws IOException {
		if (smtpSession.isMailTransactionInProcess()) {
			smtpSession.Write("503 5.5.1 Sender already specified.\r\n");
			return smtpSession;
		}
		if (commandString.trim().equals("MAIL FROM:")) {
			smtpSession.Write("501 Syntax: MAIL FROM: <address>\r\n");
			return smtpSession;
		}

		String args = this.getArgPredicate(commandString);
		if (!args.toUpperCase(Locale.ENGLISH).startsWith("FROM:")) {
			smtpSession.Write(
					"501 Syntax: MAIL FROM: <address>  Error in parameters: \"" +
							this.getArgPredicate(commandString) + "\"\r\n");
			return smtpSession;
		}

		String emailAddress = EmailUtils.extractEmailAddress(args, 5);
		if (!EmailUtils.isValidEmailAddress(emailAddress))
		{
			smtpSession.Write("553 <" + emailAddress + "> Invalid email address.\r\n");
			return smtpSession;
		}

//
//		// extract SIZE argument from MAIL FROM command.
//		// disregard unknown parameters. TODO: reject unknown
//		// parameters.
		int size = 0;
		String largs = args.toLowerCase(Locale.ENGLISH);
		System.out.println(largs);
		int sizec = largs.indexOf(" size=");
		if (sizec > -1)
		{
			// disregard non-numeric values.
			String ssize = largs.substring(sizec + 6).trim();
			if (ssize.length() > 0 && ssize.matches("[0-9]+"))
			{
				size = Integer.parseInt(ssize);
			}
		}
		// Reject the message if the size supplied by the client
		// is larger than what we advertised in EHLO answer.
		if (size > ServerConfig.MaxMessageSize)
		{
			smtpSession.Write("552 5.3.4 Message size exceeds fixed limit\r\n");
			return smtpSession;
		}
//
//		sess.setDeclaredMessageSize(size);
		smtpSession.Write("250 Ok\r\n");
		smtpSession.setMailTransactionInProcess(true);
		EmailModel emailModel =smtpSession.getEmailModel();
		emailModel.setFrom(emailAddress);
		smtpSession.setEmailModel(emailModel);
		return smtpSession;
//
//		try
//		{
//			sess.getMessageHandler().from(emailAddress);
//		}
//		catch (DropConnectionException ex)
//		{
//			// roll back the start of the transaction
//			sess.resetMailTransaction();
//			throw ex; // Propagate this
//		}
//		catch (RejectException ex)
//		{
//			// roll back the start of the transaction
//			sess.resetMailTransaction();
//			sess.sendResponse(ex.getErrorResponse());
//			return;
//		}
//
//		sess.sendResponse("250 Ok");
//	}
//		return preState;
	}

}
