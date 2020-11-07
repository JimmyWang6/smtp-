package net.novaborn.smtp.server;
import io.netty.channel.Channel;
import net.novaborn.config.ServerConfig;
import net.novaborn.entity.EmailModel;
import net.novaborn.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

public class SmtpSession {
    private static ServerConfig serverConfig;
    private Channel channel;
    private EmailModel emailModel = new EmailModel();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    StringBuilder buf = new StringBuilder();
    private String hello;
    private int recipientCount = 0;
    private final static Logger log = LoggerFactory.getLogger(SmtpSession.class);
    private boolean isMailTransactionInProcess = false;
    private int loginProcessCount = 0;
    private boolean isLogin = false;
    private boolean isDataProcess = false;
    private boolean isLoginProcess = false;
    private User user = new User();

    public SmtpSession(Channel channel) {
        this.channel = channel;
    }
    public void Write(String str){
        log.info(sdf.format(new java.util.Date())+" -[服务器]发送: "+str.substring(0,str.length()-2));
        this.channel.writeAndFlush(str);
    }

    public StringBuilder getBuf() {
        return buf;
    }

    public void setBuf(StringBuilder buf) {
        this.buf = buf;
    }

    public static ServerConfig getServerConfig() {
        return serverConfig;
    }

    public static void setServerConfig(ServerConfig serverConfig) {
        SmtpSession.serverConfig = serverConfig;
    }

    public EmailModel getEmailModel() {
        return emailModel;
    }

    public void setEmailModel(EmailModel emailModel) {
        this.emailModel = emailModel;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }


    public void send(EmailModel emailModel){}

    public void resetMailTransaction()
    {
        this.emailModel = new EmailModel();
        this.setMailTransactionInProcess(false);
        this.setDataProcess(false);
    }
    public void sendMail() throws Exception{

    }

    public boolean isDataProcess() {
        return isDataProcess;
    }

    public void setDataProcess(boolean dataProcess) {
        isDataProcess = dataProcess;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getHello() {
        return hello;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public int getLoginProcessCount() {
        return loginProcessCount;
    }

    public void setLoginProcessCount(int loginProcessCount) {
        this.loginProcessCount = loginProcessCount;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public int getRecipientCount() {
        return recipientCount;
    }

    public void setRecipientCount(int recipientCount) {
        this.recipientCount = recipientCount;
    }

    public static Logger getLog() {
        return log;
    }

    public boolean isMailTransactionInProcess() {
        return isMailTransactionInProcess;
    }

    public void setMailTransactionInProcess(boolean mailTransactionInProcess) {
        isMailTransactionInProcess = mailTransactionInProcess;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isLoginProcess() {
        return isLoginProcess;
    }

    public void setLoginProcess(boolean loginProcess) {
        isLoginProcess = loginProcess;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
