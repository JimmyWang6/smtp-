package net.novaborn.smtp.command;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import net.novaborn.smtp.server.BaseCommand;
import net.novaborn.smtp.server.SmtpSession;

import java.io.IOException;

/**
 * @author Ian McFarland &lt;ian@neo.com&gt;
 * @author Jon Stevens
 * @author Jeff Schnitzer
 */
public class QuitCommand extends BaseCommand
{
	/** */
	public QuitCommand()
	{
		super("QUIT");
	}

	/** */

//	@Override
//	public String execute(String preState, String commandString, Channel channel, ServerConfig serverConfig, CommandHandler commandHandler) throws IOException {
//		ChannelFuture future = channel.writeAndFlush("221 Bye\r\n");
//		future.addListener(ChannelFutureListener.CLOSE);
//		return null;
//	}

	@Override
	public SmtpSession execute(String commandString, SmtpSession smtpSession) throws IOException {
		ChannelFuture future = smtpSession.getChannel().writeAndFlush("221 Bye\r\n");
		future.addListener(ChannelFutureListener.CLOSE);
		return null;
	}
}
