package com.tlkj.cod.admin.service.impl;

import com.sun.mail.util.MailSSLSocketFactory;
import com.tlkj.cod.admin.service.CodAdminSendEmailService;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName: SendEmailServiceImpl
 * @Description: 发送邮件
 * @Author yjk
 * @Date 2019/6/4 4:59 PM
 */
@Service
public class CodAdminSendEmailServiceImpl implements CodAdminSendEmailService {

    /**
     * 邮件服务器主机名
     * QQ邮箱的 SMTP 服务器地址为: smtp.qq.com
     */
    private static String myEmailSMTPHost = "smtp.qq.com";
    /**
     * 发件人邮箱
     */
    private static String myEmailAccount = "245640124@qq.com";
    /**
     * 发件人邮箱密码（授权码）
     * 在开启SMTP服务时会获取到一个授权码，把授权码填在这里
     */
    private static String myEmailPassword = "exmpetvsckiebhfi";


    /**
     * 发送邮件
     *
     * @param toEmailAddress 收件箱地址
     * @param emailTitle     邮件主题
     * @param emailContent   邮件内容
     * @return
     */
    @Override
    public boolean sendEmail(String toEmailAddress, String emailTitle, String emailContent, String filename) {
        Properties props = new Properties();
        // 开启debug调试
        //props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 端口号
        props.put("mail.smtp.port", 465);
        // 设置邮件服务器主机名
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            //设置是否使用ssl安全连接（一般都使用）
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);
            //创建会话
            Session session = Session.getInstance(props);
            //获取邮件对象
            //发送的消息，基于观察者模式进行设计的
            Message msg = new MimeMessage(session);
            //设置邮件标题
            msg.setSubject(emailTitle);
            //设置邮件内容
            StringBuilder builder = new StringBuilder();
            //写入内容
            builder.append("\n" + emailContent);
            //设置显示的发件时间 默认立即发送
            msg.setSentDate(new Date());
            //设置邮件内容
            msg.setText(builder.toString());
            // 设置要发送附件的文件路径
//            DataSource source = new FileDataSource(filename);
//            msg.setDataHandler(new DataHandler(source));
//            msg.setFileName(MimeUtility.encodeText(filename));
            //设置发件人邮箱
            // InternetAddress 的三个参数分别为: 发件人邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
            msg.setFrom(new InternetAddress(myEmailAccount));
            //得到邮差对象
            Transport transport = session.getTransport();
            //连接自己的邮箱账户
            //密码不是自己QQ邮箱的密码，而是在开启SMTP服务时所获取到的授权码
            transport.connect(myEmailSMTPHost, myEmailAccount, myEmailPassword);
            //发送邮件
            transport.sendMessage(msg, new Address[]{new InternetAddress(toEmailAddress)});
//            //将该邮件保存到本地
//            OutputStream out = new FileOutputStream("MyEmail.eml");
//            msg.writeTo(out);
//            out.flush();
//            out.close();
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
