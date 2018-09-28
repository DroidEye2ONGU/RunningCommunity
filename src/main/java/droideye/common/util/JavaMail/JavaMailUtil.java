package droideye.common.util.JavaMail;

import com.sun.mail.util.MailSSLSocketFactory;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class JavaMailUtil {

    private Properties properties = new Properties();

    public JavaMailUtil() throws Exception{

        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\DroidEye\\Desktop\\Programme\\IdeaProjects\\RunningCommunity\\target\\classes\\JavaMail.properties"));

        properties.load(bufferedReader);

        //properties.setProperty("mail.debug", "true");
        //properties.setProperty("mail.host", "smtp.qq.com");
        //properties.setProperty("mail.smtp.auth", "true");
        //properties.setProperty("mail.transport.protocol", "smtp");
        //
        //
        //properties.setProperty("mail.smtp.ssl.enable", "true");
    }

    public void sendEmail(String destination, String subject, String text) throws Exception {
        MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
        mailSSLSocketFactory.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);

        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        //创建邮件
        MimeMessage message = createEmail(session,subject,text,destination);

        //获得传输通道
        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", "droideye.ongu@foxmail.com", "xauotcozdnjfbied");
        //连接并发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    private MimeMessage createEmail(Session session,String subject,String text,String destination) throws Exception {
        //根据会话创建邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        InternetAddress fromAddress = new InternetAddress("droideye.ongu@foxmail.com", "DroidEye", "utf-8");
        //设置发送邮件房
        mimeMessage.setFrom(fromAddress);

        //设置邮件接收方
        InternetAddress receiveAddress = new InternetAddress(destination);
        mimeMessage.setRecipient(Message.RecipientType.TO, receiveAddress);

        //设置邮件标题
        mimeMessage.setSubject(subject, "UTF-8");
        mimeMessage.setText(text);

        //设置显示的发件时间
        mimeMessage.setSentDate(new Date());

        //保存设置
        mimeMessage.saveChanges();

        return mimeMessage;
    }

/*    public static void main(String[] args) throws Exception {
        JavaMailUtil javaMailUtil = new JavaMailUtil();
        javaMailUtil.sendEmail("512892702@qq.com", "您的新密码已生成", "老王在窗边");

    }*/
}
