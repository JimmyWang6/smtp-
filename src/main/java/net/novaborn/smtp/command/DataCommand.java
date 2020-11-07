package net.novaborn.smtp.command;//package command;
import net.novaborn.smtp.server.BaseCommand;
import net.novaborn.smtp.server.SmtpSession;
import java.io.IOException;

/**
 * @author Ian McFarland &lt;ian@neo.com&gt;
 * @author Jon Stevens
 * @author Jeff Schnitzer
 */
public class DataCommand extends BaseCommand
{
	private final static int BUFFER_SIZE = 1024 * 32;	// 32k seems reasonable

	/** */
	public DataCommand()
	{
		super("DATA");
	}

	/** */
	@Override
	public SmtpSession execute(String commandString, SmtpSession smtpSession)
			throws IOException {
        if (!smtpSession.isMailTransactionInProcess()) {
            smtpSession.Write("503 5.5.1 Error: need MAIL command\r\n");
            return smtpSession;
        } else if (smtpSession.getEmailModel().getTo().size() == 0) {
            smtpSession.Write("503 Error: need RCPT command\r\n");
            return smtpSession;
        }
        smtpSession.Write("354 End data with <CR><LF>.<CR><LF>\r\n");
        smtpSession.setDataProcess(true);
        smtpSession.setBuf(new StringBuilder());
        return smtpSession;
//		InputStream stream = sess.getRawInput();
//		stream = new BufferedInputStream(stream, BUFFER_SIZE);
//		stream = new DotTerminatedInputStream(stream);
//		stream = new DotUnstuffingInputStream(stream);
//		if (!sess.getServer().getDisableReceivedHeaders())
//		{
//			stream = new ReceivedHeaderStream(stream, sess.getHelo(), sess
//					.getRemoteAddress().getAddress(), sess.getServer()
//					.getHostName(), sess.getServer().getSoftwareName(),
//					sess.getSessionId(), sess.getSingleRecipient());
//		}
//
//		try
//		{
//			sess.getMessageHandler().data(stream);
//
//			// Just in case the handler didn't consume all the data, we might as well
//			// suck it up so it doesn't pollute further exchanges.  This code used to
//			// throw an exception, but this seems an arbitrary part of the contract that
//			// we might as well relax.
//			while (stream.read() != -1)
//				;
//		}
//		catch (DropConnectionException ex)
//		{
//			throw ex; // Propagate this
//		}
//		catch (RejectException ex)
//		{
//			sess.sendResponse(ex.getErrorResponse());
//			return;
//		}
//
//		sess.sendResponse("250 Ok");
//		sess.resetMailTransaction();
//	}
    }
}
