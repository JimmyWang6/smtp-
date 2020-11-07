package net.novaborn.pop.server;

import net.novaborn.exceptions.CommandException;
import net.novaborn.exceptions.DropConnectionException;
import net.novaborn.exceptions.InvalidCommandNameException;
import net.novaborn.exceptions.UnknownCommandException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

public class CommandHandler {

    // The map of known SMTP commands. Keys are upper case names of the commands.
    private Map<String, Command> commandMap = new HashMap<String, Command>();

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
     * 处理命令
     */
    public void handleCommand(PopSession popSession, String commandString)
            throws SocketTimeoutException, IOException, DropConnectionException {
        try {
            Command command = this.getCommandFromString(commandString);
            command.execute(commandString, popSession);
        } catch (CommandException e) {
            popSession.Write("-Err " + e.getMessage() + "\r\n");
        }
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
            throw new UnknownCommandException("command not implemented");
        }
        return command;
    }

    /**
     * 取前四个英文字母作为命令
     */
    private String toKey(String string) throws InvalidCommandNameException {
        if (string == null || string.length() < 4)
            throw new InvalidCommandNameException("bad syntax");

        return string.substring(0, 4).toUpperCase(Locale.ENGLISH);
    }

    /**
     * 如果该行命令里没有空格分隔就报错
     */
    private String toVerb(String string) throws InvalidCommandNameException {
        StringTokenizer stringTokenizer = new StringTokenizer(string);
        if (!stringTokenizer.hasMoreTokens())
            throw new InvalidCommandNameException("bad syntax");

        return stringTokenizer.nextToken().toUpperCase(Locale.ENGLISH);
    }
}
