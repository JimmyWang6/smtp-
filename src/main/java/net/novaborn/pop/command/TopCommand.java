package net.novaborn.pop.command;

import net.novaborn.entity.Mailbox;
import net.novaborn.pop.server.BaseCommand;
import net.novaborn.pop.server.PopSession;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * top msg# n  用于获取某封邮件的邮件头和邮件体中的前n行内容，参数msg#表示邮件的序号，参数n表示要返回邮件的前几行内容。
 */
public class TopCommand extends BaseCommand {

    public TopCommand() {
        super("TOP");
    }

    @Override
    public void execute(String commandString, PopSession popSession) throws IOException {
        String[] args = this.getArgs(commandString);
        List<Mailbox> emailList = popSession.getEmail();
        if (args.length < 3) {
            popSession.Write("-Err bad syntax\r\n");
        } else {
            try {
                int index = Integer.parseInt(args[1]);
                int topLine = Integer.parseInt(args[2]);
                if (index <= 0 || topLine < 0) {    // 第一个参数必须为正整数，第二个参数必须为自然数
                    popSession.Write("-Err the first parameter must be a positive integer and the second parameter must be a natural number\r\n");
                } else if (index > emailList.size()) {
                    popSession.Write("-Err Message not exists");
                } else {
                    // 用户输入1，其实是查看emaillist里第0个信件
                    Mailbox email = emailList.get(index - 1);
                    popSession.Write("+OK\r\n");
                    if (email.getDate() == null) {
                        popSession.Write(".\r\n");
                    } else {
                        // 将信件内容按照换行符分割开来，分隔符只能有一个，如果delim参数使用"\r\n"的话，意思是\r和\n都作为分隔符
                        StringTokenizer stringTokenizer = new StringTokenizer(email.getData(), "\n", true);
                        while (stringTokenizer.hasMoreTokens()) {
                            String line = stringTokenizer.nextToken();
                            popSession.getChannel().writeAndFlush(line);
                            if (line.equals("\r")) {    // 如果遇到一个内容只有一个'\r'字符，说明遇到了一个空行，邮件头已经打印完了
                                popSession.getChannel().writeAndFlush(stringTokenizer.nextToken());    // 把该空行的'\n'字符也打印出来
                                break;
                            }
                        }

                        // 一行文本包括：1、字符串加上\r， 2、\n
                        topLine *= 2;
                        while (stringTokenizer.hasMoreTokens() && topLine > 0) {
                            popSession.getChannel().writeAndFlush(stringTokenizer.nextToken());
                            topLine--;
                        }
                        popSession.Write(".\r\n");
                    }
                }
            } catch (NumberFormatException e) {
                popSession.Write("-Err the parameter must be a positive integer\r\n");
            }
        }
    }
}
