package com.clearlove.utils;

import com.clearlove.pojo.User;
import com.sun.mail.util.MailSSLSocketFactory;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author promise
 * @date 2023/3/1 - 22:31
 * 网站3s原则：用户体验
 * 多线程实现用户体验：异步处理
 */
public class SendEmail extends Thread {

    // 用于给用户发送邮件的邮箱
    private String from = "976949689@qq.com";

    // 邮箱的用户名
    private String username = "976949689@qq.com";

    // 邮箱的密码
    private String password = "jpnaegmzbomebbeg";

    // 发送邮件的服务器地址
    private String host = "smtp.qq.com";

    private User user;

    public SendEmail(User user) {
      this.user = user;
    }

    // 重写run方法的实现，在run方法中发送邮件给指定的用户


  @Override
  public void run() {
    try {
      Properties prop = new Properties();
      prop.setProperty("mail.host", host);
      prop.setProperty("mail.transport.protocol","smtp");
      prop.setProperty("mail.smtp.auth","true");

      // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可，其他邮箱不需要
      MailSSLSocketFactory sf = new MailSSLSocketFactory();
      sf.setTrustAllHosts(true);
      prop.put("mail.smtp.ssl.enable", "true");
      prop.put("mail.smtp.ssl.socketFactory", sf);

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
      message.setFrom(new InternetAddress(from));

      // 指明邮件的收件人，现在收件人和发件人是一样的，就是自己给自己发
      message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));

      // 邮件的标题
      message.setSubject("用户注册邮件");
//    // 邮件的文本内容
      String info = "恭喜您注册成功，您的用户名：" + user.getUsername() + "，您的密码：" + user.getPassword() + "，请妥善保管，如有问题请联系网站客服！！";
      message.setContent(info, "text/html;charset=UTF-8");
      message.saveChanges();

      // 5.发送邮件
      ts.sendMessage(message, message.getAllRecipients());

      // 6.关闭连接
      ts.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
