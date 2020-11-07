package net.novaborn.smtp.server;
import net.novaborn.comm.SpringContextHolder;
import net.novaborn.dao.MailboxMapper;
import net.novaborn.dao.UserMapper;
import net.novaborn.entity.Mailbox;
import net.novaborn.util.Base64;
import net.novaborn.entity.EmailModel;
import net.novaborn.entity.User;
import net.novaborn.entity.UserExample;
import net.novaborn.exceptions.CommandException;
import net.novaborn.exceptions.DropConnectionException;
import net.novaborn.exceptions.InvalidCommandNameException;
import net.novaborn.exceptions.UnknownCommandException;
import net.novaborn.util.TextUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
public class CommandHandler {

    private static final Logger log = Logger.getLogger(CommandHandler.class.getName());
    /**
     * The map of known SMTP commands. Keys are upper case names of the commands.
     */
    private Map<String, Command> commandMap = new HashMap<String, Command>();
    public List<String> getEmais() {
        return emais;
    }

    public void setEmais(List<String> emais) {
        this.emais = emais;
    }

    private List<String> emais = new ArrayList<String>();
    private boolean isLogin = false;
    UserMapper userMapper= SpringContextHolder.getBean("userMapper");
    MailboxMapper mailboxMapper= SpringContextHolder.getBean("mailboxMapper");
    /**
     * 初始化的时候先添加已知的命令
     */
    public CommandHandler() {
        // This solution should be more robust than the earlier "manual" configuration.
        for (CommandRegistry registry : CommandRegistry.values()) {
            this.addCommand(registry.getCommand());
        }
    }

    /**
     * 添加或替换指定的命令
     */
    public void addCommand(Command command) {
        this.commandMap.put(command.getName(), command);
    }

    /**
     * Returns the command object corresponding to the specified command name.
     *
     * @param commandName case insensitive name of the command.
     * @return the command object, or null, if the command is unknown.
     */
    public Command getCommand(String commandName) {
        String upperCaseCommandName = commandName.toUpperCase(Locale.ENGLISH);
        return this.commandMap.get(upperCaseCommandName);
    }

    /**
     *
     */
    public boolean containsCommand(String command) {
        return this.commandMap.containsKey(command);
    }

    /**
     *
     */
    public Set<String> getVerbs() {
        return this.commandMap.keySet();
    }

    /**
     * 处理命令
     */
    public SmtpSession handleCommand(SmtpSession smtpSession, String commandString)
            throws IOException, DropConnectionException {
        try {
                if(!smtpSession.isMailTransactionInProcess()) {
                smtpSession.setEmailModel(new EmailModel());
            }
            if(smtpSession.isMailTransactionInProcess())
                {
                    if(!smtpSession.isDataProcess()){
                    Command command = this.getCommandFromString(commandString);
                    if(command.getName().equals("RCPT")){
                        command.execute(commandString,smtpSession);
                        return smtpSession;
                    }
                }else{
                        if (commandString.equals(".")){
                            smtpSession.Write("250 Ok\r\n");
                            smtpSession.getEmailModel().setEmailStr(smtpSession.getBuf().toString());
                            Mailbox mailbox = new Mailbox();
                            String data = smtpSession.getBuf().toString();
                            mailbox.setData(data);
                            mailbox.setId(smtpSession.getUser().getId());
                            mailbox.setSize(data.getBytes().length);
                            mailbox.setFrom(smtpSession.getEmailModel().getFrom());
                            mailbox.setDate(new Date());
                            for (String str:smtpSession.getEmailModel().getTo()
                                 ) {
                                mailbox.setTo(str);
                                mailboxMapper.insert(mailbox);
                            }
//                            MimeMessage mimeMessage = GreenMailUtil.newMimeMessage(smtpSession.getBuf().toString());
//
                            smtpSession.resetMailTransaction();
                            return smtpSession;
                        }
                        else if(commandString.startsWith(".")){
                           smtpSession.setBuf(println(smtpSession.getBuf(),commandString.substring(1)));
                           return smtpSession;
                        }else{
                            smtpSession.setBuf(println(smtpSession.getBuf(),commandString));
                        }
                        return smtpSession;
                    }

            }

            int login_process = smtpSession.getLoginProcessCount();
            if(login_process==1){
                byte[] decoded = Base64.decode(commandString);
                if (decoded == null)
                {
                    smtpSession.Write(
                            "501 Invalid command argument, not a valid Base64 string\r\n");
                    return smtpSession;
                }


                smtpSession.getUser().setUserName(TextUtils.getStringUtf8(decoded));
                smtpSession.Write("334 UGFzc3dvcmQ6\r\n");
                smtpSession.setLoginProcessCount(2);
                return smtpSession;
            }
            else if(login_process==2){
                byte[] decoded = Base64.decode(commandString);
                if (decoded == null)
                {
                    smtpSession.Write(
                            "501 Invalid command argument, not a valid Base64 string\r\n");
                    return smtpSession;
                }
                String password = TextUtils.getStringUtf8(decoded);
                UserExample userExample = new UserExample();
                userExample.createCriteria().andPasswordEqualTo(password);
                userExample.createCriteria().andUserNameEqualTo(smtpSession.getUser().getUserName());
                List<User> myuser = userMapper.selectByExample(userExample);
                if(myuser.size()==0){
                    System.out.println("0");
                   smtpSession.setUser(new User());
                   smtpSession.Write("535 Error: authentication failed\r\n");
                   smtpSession.setLoginProcessCount(0);
                   smtpSession.setLogin(false);
                   return smtpSession;
                }
                else{
                    System.out.println("登陆成功");
                    smtpSession.Write("235 auth successfully\r\n");
                    smtpSession.setLogin(true);
                    smtpSession.setUser(myuser.get(0));
                    smtpSession.setLoginProcessCount(0);
                    return smtpSession;
                }
            }
            Command command = this.getCommandFromString(commandString);
            command.execute(commandString,smtpSession);
        }catch (UnknownCommandException e){
            smtpSession.Write("502 " + e.getMessage()+"\r\n");
            smtpSession.setLoginProcessCount(0);
        }
       catch (CommandException e) {
            smtpSession.Write("500 " + e.getMessage()+"\r\n");
           smtpSession.setLoginProcessCount(0);
        }
        return smtpSession;
    }

    /**
     * 给定字符串获得命令对象
     * @param commandString 客户输入的字符串
     * @return 返回命令对象
     * @throws UnknownCommandException     未知的命令
     * @throws InvalidCommandNameException 无效的命令名称，具体而言就是没有空格来区分命令和参数
     */
    private Command getCommandFromString(String commandString)
            throws UnknownCommandException, InvalidCommandNameException {
        Command command = null;
        // 取该行的前四个英文字母作为命令
        String key = this.toKey(commandString);
        command = this.commandMap.get(key);
        if (command == null) {  // 如果前四个字母不是已知的命令，那有可能是自定义的超过四个字母的命令
            // some commands have a verb longer than 4 letters
            String verb = this.toVerb(commandString);
            command = this.commandMap.get(verb);
        }
        if (command == null) {  // 如果还是找不到，说明这是一个未知的命令
            throw new UnknownCommandException("Error: command not implemented");
        }
        return command;
    }

    /**
     * 取前四个英文字母作为命令
     */
    private String toKey(String string) throws InvalidCommandNameException {
        if (string == null || string.length() < 4)
            throw new InvalidCommandNameException("Error: bad syntax");

        return string.substring(0, 4).toUpperCase(Locale.ENGLISH);
    }

    /**
     * 如果该行命令里没有空格分隔就报错
     */
    private String toVerb(String string) throws InvalidCommandNameException {
        StringTokenizer stringTokenizer = new StringTokenizer(string);
        if (!stringTokenizer.hasMoreTokens())
            throw new InvalidCommandNameException("Error: bad syntax");
        return stringTokenizer.nextToken().toUpperCase(Locale.ENGLISH);
    }

    private StringBuilder println(StringBuilder buf, String line) {
        buf.append(line).append("\r\n");
        return buf;
    }
}
