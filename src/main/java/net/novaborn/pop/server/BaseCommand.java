package net.novaborn.pop.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

abstract public class BaseCommand implements Command
{
    @SuppressWarnings("unused")
    private final static Logger log = Logger.getLogger(BaseCommand.class.getName());

    /** Name of the command, ie HELO */
    private String name;

    /** */
    protected BaseCommand(String name)
    {
        this.name = name;
    }

    /**
     * This is the main method that you need to override in order to implement a command.
     */
    abstract public void execute(String commandString, PopSession smtpSession) throws IOException;


    /** */
    public String getName()
    {
        return this.name;
    }

    /** */
    protected String getArgPredicate(String commandString)
    {
        if (commandString == null || commandString.length() < 4)
            return "";

        return commandString.substring(4).trim();
    }

    /** */
    protected String[] getArgs(String commandString)
    {
        List<String> strings = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(commandString);
        while (stringTokenizer.hasMoreTokens())
        {
            strings.add(stringTokenizer.nextToken());
        }

        return strings.toArray(new String[0]);
    }
}
