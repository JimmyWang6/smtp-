package net.novaborn.exceptions;

public class DropConnectionException extends RejectException
{
    int code;

    /** */
    public DropConnectionException()
    {
        super();
    }

    /** */
    public DropConnectionException(String message)
    {
        super(message);
    }

    /** */
    public DropConnectionException(int code, String message)
    {
        super(code, message);
    }
}
