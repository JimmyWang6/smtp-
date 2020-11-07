package net.novaborn.smtp.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.smtp.DefaultSmtpResponse;
import io.netty.handler.codec.smtp.SmtpCommand;
import io.netty.handler.codec.smtp.SmtpRequestEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
//import io.netty.handler.codec.LineBasedFrameDecoder;
//import io.netty.handler.codec.smtp.SmtpResponseDecoder;


public class SmtpServerInitializer extends ChannelInitializer<Channel> {
//	@Override
//	protected void initChannel(Channel channel) throws Exception {
//		ChannelPipeline pipeline =channel.pipeline();
//		DelimiterBasedFrameDecoder
//		pipeline.addLast(new SmtpResponseDecoder(64*1024));
//		pipeline.addLast(new SmtpResponseDecoder(64*1024));
////		pipeline.addLast(new CmdDecoder.CmdHandler());
//	}
//
//	public static final class Cmd{
//		public ByteBuf getName() {
//			return name;
//		}
//
//		public ByteBuf getArgs() {
//			return args;
//		}
//
//		private final ByteBuf name;
//		private final ByteBuf args;
//
//		public Cmd(ByteBuf name, ByteBuf args) {
//			this.name = name;
//			this.args = args;
//		}
//	}
//
//	public  class CmdDecoder extends SmtpResponseDecoder{
//		final byte SPACE=(byte)' ';
//
//		public CmdDecoder(int maxLength){
//			super(maxLength);
//		}
//
//		@Override
//		protected  Object decode(ChannelHandlerContext ctx,ByteBuf byteBuf)throws Exception{
//			ByteBuf frame = (ByteBuf) super.decode(ctx,byteBuf);
//			if(frame==null){
//				System.out.println("没有针");
//				return null;
//			}
//			int index = frame.indexOf(frame.readerIndex(),frame.writerIndex(),SPACE);
//			return new Cmd(frame.slice(frame.readerIndex(),index),frame.slice(index+1,frame.writerIndex()));
//		}
		public static final class  CmdHandler extends SimpleChannelInboundHandler<DefaultSmtpResponse>{

			@Override
			protected void channelRead0(ChannelHandlerContext channelHandlerContext,  DefaultSmtpResponse cmd) throws Exception {
				;
			}
		}

//	@Override
//	protected void initChannel(SocketChannel ch) throws Exception {
//		ChannelPipeline pipeline = ch.pipeline();
//		EmbeddedChannel channel = (EmbeddedChannel) pipeline.channel();
////		pipeline.addLast("decoder",new SmtpResponseDecoder(64*1024));
////		pipeline.addLast("encoder",new SmtpRequestEncoder());
//		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
//		pipeline.addLast("decoder", new StringDecoder(Charset.forName("utf-8")));
//		pipeline.addLast("encoder", new StringEncoder(Charset.forName("utf-8")));
//		pipeline.addLast("handler", new SmtpServerHandler());
//	}

	@Override
	protected void initChannel(Channel channel) throws Exception {
		ChannelPipeline pipeline = channel.pipeline();
		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		pipeline.addLast("decoder",new StringDecoder());
		pipeline.addLast("encoder",new StringEncoder());
		pipeline.addLast("handler", new SmtpServerHandler());
	}
}