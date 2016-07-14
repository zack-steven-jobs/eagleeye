package com.cmsz.eagleeye.util;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTest {
    /*
    *//**
     * @field props Properties
     * 
     * @Description: TODO Properties配置文件
     */
    /*
     * private final Properties props = System.getProperties();
     *//**
     * @field authenticator MailAuthenticator
     * 
     * @Description: TODO 邮件服务器登陆认证
     */
    /*
     * private MailAuthenticator authenticator;
     *//**
     * @field session Session
     * 
     * @Description: TODO 邮箱session
     */
    /*
     * private Session session;
     *//**
     * 创建一个新的实例 MailSender.
     * 
     * @param smtpHostName SMTP邮件服务器地址
     * @param username 发送人的用户名
     * @param password 发送邮件的密码
     */
    /*
     * public MailTest(final String smtpHostName, final String username, final String
     * password) { init(username, password, smtpHostName); }
     *//**
     * 创建一个新的实例 MailSender.
     * 
     * @param username 发送邮件的用户名(地址)，并以此解析SMTP服务器地址
     * @param password 发送邮件的密码
     */
    /*
     * 
     * public MailTest(final String username, final String password) { final String
     * smtpHostName = "smtp." + username.split("@")[1]; init(username, password,
     * smtpHostName);
     * 
     * }
     *//**
     * init
     * 
     * @Description 初始化
     * @param username 发送邮件的用户名(地址)
     * @param password 密码
     * @param smtpHostName SMTP主机地址
     * @return void
     */
    /*
     * private void init(String username, String password, String smtpHostName) { //
     * 初始化props props.put("mail.debug", "true"); props.put("mail.smtp.auth", "true");
     * props.put("mail.smtp.host", smtpHostName); //
     * props.put("mail.smtp.auth.mechanisms", "NTLM");
     * 
     * authenticator = new MailAuthenticator(username, password); session =
     * Session.getInstance(props, authenticator); }
     *//**
     * send
     * 
     * @Description 发送邮件
     * @param recipient 收件人邮箱地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @throws AddressException
     * @throws MessagingException
     * @return void
     */
    /*
     * public void send(String recipient, String subject, Object content) throws
     * AddressException, MessagingException { // 创建mime类型邮件 final MimeMessage message =
     * new MimeMessage(session); // 设置发信人 message.setFrom(new
     * InternetAddress(authenticator.getUsername())); // 设置收件人
     * message.setRecipient(RecipientType.TO, new InternetAddress(recipient)); // 设置主题
     * message.setSubject(subject); // 设置邮件内容 message.setContent(content.toString(),
     * "text/html;charset=utf-8"); // 发送 Transport.send(message);
     * 
     * }
     *//**
     * send
     * 
     * @Description 群发邮件
     * @param recipients 收件人们
     * @param subject 主题
     * @param content 内容
     * @throws AddressException
     * @throws MessagingException
     * @return void
     */
    /*
     * public void send(List<String> recipients, String subject, Object content) throws
     * AddressException, MessagingException {
     * 
     * final MimeMessage message = new MimeMessage(session); message.setFrom(new
     * InternetAddress(authenticator.getUsername())); // 设置收件人们 final int num =
     * recipients.size(); InternetAddress[] addresses = new InternetAddress[num]; for (int
     * i = 0; i < num; i++) { addresses[i] = new InternetAddress(recipients.get(i)); }
     * message.setRecipients(RecipientType.TO, addresses); // 设置主题
     * message.setSubject(subject); // 设置邮件内容 message.setContent(content.toString(),
     * "text/html;charset=utf-8"); // 发送 Transport.send(message); }
     * 
     * public static void main(String[] args) throws Exception { //MailTest test = new
     * MailTest("smtp.163.com", "chluknight@163.com", "kiko831020");
     * 
     * MailTest test = new MailTest("192.168.2.56", "upay@chinamobilesz.com",
     * "jiaoyi%520");
     * 
     * 
     * test.send("105279906@qq.com", "测试邮件", "hehe");
     * 
     * }
     * 
     * 
     * }
     * 
     * 
     * class MailAuthenticator extends Authenticator {
     *//**
     * @field username String
     * 
     * @Description: TODO 用户名
     */
    /*
     * private String username;
     *//**
     * @field password String
     * 
     * @Description: TODO 密码
     */
    /*
     * private String password;
     *//**
     * 创建一个新的实例 MailAuthenticator.
     * 
     * @param username
     * @param password
     */
    /*
     * public MailAuthenticator(String username, String password) { this.username =
     * username; this.password = password; }
     * 
     * String getPassword() { return password; }
     * 
     * @Override protected PasswordAuthentication getPasswordAuthentication() { return new
     * PasswordAuthentication(username, password); }
     * 
     * public String getUsername() { return username; }
     * 
     * public void setPassword(String password) { this.password = password; }
     * 
     * public void setUsername(String username) { this.username = username; }
     * 
     * // mail.userName=jiaobocheng_test@163.com // mail.passWord=jiaobc_test //
     * mail.smtpHost=smtp.163.com // // // #mail.userName=upay@chinamobilesz.com //
     * #mail.passWord=jiaoyi%520 // #mail.smtpHost=mail.chinamobilesz.com
     */
    public static void main(String[] args) {
        // 这个类主要是设置邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("192.168.2.56");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("upay@chinamobilesz.com");
        mailInfo.setPassword("jiaoyi%520");// 您的邮箱密码
        mailInfo.setFromAddress("upay@chinamobilesz.com");
        mailInfo.setToAddress("105279906@qq.com");
        mailInfo.setSubject("hello");
        mailInfo.setContent("hello");
        // 这个类主要来发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        sms.sendTextMail(mailInfo);// 发送文体格式
        sms.sendHtmlMail(mailInfo);// 发送html格式
    }

}
