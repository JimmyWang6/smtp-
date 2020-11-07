package net.novaborn.smtp;

import net.novaborn.dao.MailboxMapper;
import net.novaborn.dao.UserMapper;
import net.novaborn.entity.MailboxExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmtpServerApplicationTests {
    public static String myEmailAccount = "test";
    public static String myEmailPassword = "test";

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易126邮箱的 SMTP 服务器地址为: smtp.126.com
    public static String myEmailSMTPHost = "127.0.0.1";

    // 收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMailAccount = "123456@qq.com";
    public static String receiveMailAccount1 = "1234355556@qq.com";
    @Test
    public void contextLoads() throws Exception {
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);
        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接


    }

    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "昵称", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));
        message.addRecipients(MimeMessage.RecipientType.TO, "961370183@qq.com");
        // 4. Subject: 邮件主题
        message.setSubject("我是谁 你是谁 我爱你 你爱我", "UTF-8");
        MimeMultipart multipart=new MimeMultipart();
        MimeBodyPart part1=new MimeBodyPart();
        part1.setContent("这是一份带有附件的测试邮件","text/html;charset=utf-8");
        //添加主体部件
        multipart.addBodyPart(part1);
        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("邮件正文knrnjfjfnjfjfn", "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }

    @Autowired
    UserMapper userMapper;

    @Autowired
    MailboxMapper emailMapper;

    @Test
    public void getUser() {
//        System.out.println(userMapper.selectByUserName("123@qq.com"));

        MailboxExample mailboxExample = new MailboxExample();
        mailboxExample.createCriteria().andFromEqualTo("123@qq.com");
        System.out.println(emailMapper.selectByExample(mailboxExample));
    }

}
