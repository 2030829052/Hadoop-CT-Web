package org.fengyue.hadoopctweb.entity.utils;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailUtis {
    /**
     * 邮箱找回密码的方法
     * receiverEmail 收件人
     * username ==用户的账号
     * password==用户的密码
     */
    private static final String AccountEmail = "fy2030829052@163.com"; //我的163邮箱  发件人


    /**
     * 发送一个邮件
     */
    private static final String AccountPassword = "CHPKTUBPDEBVWCTY"; //smtp服务器认证的密码
    private static final String AccountHost = "smtp.163.com";   //smtp服务器
    static Properties props = new Properties();

    public static boolean isMail(String email) {
        boolean flag = false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(email);
        if (m.matches())
            flag = true;
        else
            System.out.println("输入邮箱格式错误......");
        return flag;
    }

    public static void sendEmail(String receiverEmail, String username, String password) throws Exception {
        //设置参数
        props.setProperty("mail.transport.protocol", "smtp");//需要遵循的协议
        props.setProperty("mail.smtp.host", AccountHost);//服务器的认证
        props.setProperty("mail.smtp.auth", "true");//邮箱认证
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.starttls.required", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        //让参数生效
        Session session = Session.getDefaultInstance(props);
        //获取发送邮件的对象
        Transport transport = session.getTransport();
        //获取连接  发件人
        transport.connect(AccountEmail, AccountPassword);
        //创建一封邮件
        Message message = getMessage(session, AccountEmail, receiverEmail, username, password);
        //发送邮件  Message msg, Address[] addresses
        transport.sendMessage(message, message.getAllRecipients());
        //关流
        transport.close();
    }


    /**
     * 写一封邮件
     */
    private static Message getMessage(Session session, String sendMail, String receiverMail, String username, String password) throws Exception {
        //mimeMessage 写邮件的对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //开始整理  发件人  InternetAddress
        //String address, String personal, String charset
        mimeMessage.setFrom(new InternetAddress(sendMail, "风月", "utf-8"));
        //收件人  RecipientType type, Address address
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receiverMail, username + "用户", "utf-8"));

        //主题
        mimeMessage.setSubject("密码找回！！！");

        //时间
        mimeMessage.setSentDate(new Date());

        //正文
        mimeMessage.setContent("尊敬的用户,您的的账号是:" + username + ",您的密码是:" + password, "text/html;charset=utf-8");

        //保存
        mimeMessage.saveChanges();

        return mimeMessage;
    }


}
