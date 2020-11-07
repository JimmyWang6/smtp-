package net.novaborn.smtp.server;


import io.netty.channel.Channel;

import java.io.IOException;


public interface Command {
    /**
     * 执行命令
     * @param commandString 客户输入的整行命令
     */
    public SmtpSession execute(String commandString, SmtpSession smtpSession) throws IOException;

    /**
     * 返回命令的名称，格式为四个大写英文字母，例如： "QUIT"
     */
    public String getName();
}
