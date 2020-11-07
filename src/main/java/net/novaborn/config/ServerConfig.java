package net.novaborn.config;

public class ServerConfig {
    public static final String hostname = "www.wangzhiwang.online";
    public static final String dataMode = "false";
    public static final int MaxMessageSize = 1024*26;
    public static final boolean isAuthenticated = true;
    public static final boolean isPopServerRequireAuth = true; // pop3服务器是否需要先登陆才能发送邮件，默认是需要的，即不登陆就不能在这里拉屎
    public static final int MaxRecipients = 6;
}
