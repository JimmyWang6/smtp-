package net.novaborn.pop.server;

import io.netty.channel.Channel;
import net.novaborn.dao.MailboxMapper;
import net.novaborn.entity.Mailbox;
import net.novaborn.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

public class PopSession {
    private Channel channel;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static Logger log = LoggerFactory.getLogger(PopSession.class);
    private boolean isLogin = false;
    private User user = new User();
    @Autowired
    private MailboxMapper emailMapper;

    private List<Mailbox> emailList;
    private Vector<Integer> emailsToDelete = new Vector<>();
    public PopSession(Channel channel) {
        this.channel = channel;
    }
    public void Write(String str){
        log.info(sdf.format(new java.util.Date())+" -[服务器]发送: "+str.substring(0,str.length()-2));
        this.channel.writeAndFlush(str);
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean setEmail(List<Mailbox> emailList) {
        if (isLogin) {
           this.emailList = emailList;
           return this.emailList != null;
        } else {
            return false;
        }
    }

    public List<Mailbox> getEmail() {
        return this.emailList;
    }

    public Vector<Integer> getEmailsToDelete() {
        return emailsToDelete;
    }

    public void setEmailsToDelete(Vector<Integer> emailsToDelete) {
        this.emailsToDelete = emailsToDelete;
    }
}
