package Base;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class SendEmail {
    //需要3个参数：接收邮箱地址、标题、内容
    public int sendEmail(String em,String title,String text) throws MessagingException {

        //提示开始发送
        System.out.println("Start sending Email TO : "+em);


        //配置
        Properties properties = new Properties();
        properties.setProperty("mail.host","smtp.qq.com");//配置服务器
        properties.setProperty("mail.transport.protocol","smtp");//配置协议
        properties.setProperty("mail.smtp.auth","true");

        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个Session
        Session session = Session.getDefaultInstance(properties);

        //开启debug模式
        //session.setDebug(true);

        //获取连接对象
        Transport transport = null;
        transport = session.getTransport();

        //连接服务器
        transport.connect("smtp.qq.com","junhuiye@foxmail.com","tpslomnubufffgha");

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress("junhuiye@foxmail.com"));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(em));

        //邮件标题
        mimeMessage.setSubject("Hello Mail");


        //邮件内容
        mimeMessage.setContent(text,"text/html;charset=UTF-8");

        //发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();

        //提示发送成功
        System.out.println("Send successfully!");

        return 0;
    }
}
