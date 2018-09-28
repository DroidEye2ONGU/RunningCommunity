import com.sun.mail.util.MailSSLSocketFactory;

import org.junit.Test;
import javax.mail.Message.RecipientType;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailTest {

    @Test
    public void sendMailTest() throws Exception {
        Properties properties = new Properties();

        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.host", "smtp.qq.com");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.transport.protocol", "smtp");

        MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
        mailSSLSocketFactory.setTrustAllHosts(true);
        properties.setProperty("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);

        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        //创建邮件
        MimeMessage message = createEmail(session);

        //获得传输通道
        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", "droideye.ongu@foxmail.com", "xauotcozdnjfbied");
        //连接并发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public MimeMessage createEmail(Session session) throws Exception {
        //根据会话创建邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        InternetAddress fromAddress = new InternetAddress("droideye.ongu@foxmail.com", "DroidEye", "utf-8");
        //设置发送邮件房
        mimeMessage.setFrom(fromAddress);

        //设置邮件接收方
        InternetAddress receiveAddress = new InternetAddress("512892702@qq.com");
        mimeMessage.setRecipient(RecipientType.TO, receiveAddress);

        //设置邮件标题
        mimeMessage.setSubject("测试标题", "UTF-8");
        mimeMessage.setText("白裤档寒冬一鸡");

        //设置显示的发件时间
        mimeMessage.setSentDate(new Date());

        //保存设置
        mimeMessage.saveChanges();

        return mimeMessage;
    }

}
