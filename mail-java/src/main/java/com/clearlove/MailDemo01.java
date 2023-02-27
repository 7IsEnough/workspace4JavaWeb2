package com.clearlove;

import com.sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author promise
 * @date 2023/2/27 - 23:49
 * 发送一封简单邮件
 */
public class MailDemo01 {

  public static void main(String[] args) throws Exception {

    // 授权码 jpnaegmzbomebbeg

    Properties prop = new Properties();
    // 设置qq邮件服务器
    prop.setProperty("mail.host", "smtp.qq.com");
    // 邮件发送协议
    prop.setProperty("mail.transport.protocol", "smtp");
    // 需要验证用户名密码
    prop.setProperty("mail.smtp.auth", "true");

    // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可，其他邮箱不需要
    MailSSLSocketFactory sf = new MailSSLSocketFactory();
    sf.setTrustAllHosts(true);
    prop.put("mail.smtp.ssl.enable", "true");
    prop.put("mail.smtp.ssl.socketFactory", sf);

    // 使用JavaMail发送邮件的5个步骤

    // 1.创建定义整个应用程序所需的环境信息的Session对象

    // QQ才有，其他邮箱不需要
    Session session = Session.getDefaultInstance(prop, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        // 发件人邮件用户名、授权码
        return new PasswordAuthentication("976949689@qq.com", "jpnaegmzbomebbeg");
      }
    });

    // 开启session的debug模式，这样就可以查看到程序发送Email的运行状态
    session.setDebug(true);

    // 2.通过session得到transport对象
    Transport ts = session.getTransport();

    // 3.使用邮箱的用户名和授权码连上邮件服务器
    ts.connect("smtp.qq.com", "976949689@qq.com", "jpnaegmzbomebbeg");

    // 4.创建邮件：写邮件
    // 注意需要传递session
    MimeMessage message = new MimeMessage(session);

    // 指明邮件的发件人
    message.setFrom(new InternetAddress("976949689@qq.com"));

    // 指明邮件的收件人，现在收件人和发件人是一样的，就是自己给自己发
    message.setRecipient(RecipientType.TO, new InternetAddress("976949689@qq.com"));

    // 邮件的标题
    message.setSubject("欢迎来到英雄联盟");

    // 邮件的文本内容
    message.setContent("<h1 style='color: red'>你好啊！</h1>", "text/html;charset=UTF-8");


    // 5.发送邮件
    ts.sendMessage(message, message.getAllRecipients());

    // 6.关闭连接
    ts.close();




  }

}
