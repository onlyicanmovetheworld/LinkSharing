package com.futureCorp.holder;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public interface SendEmail {

    final String password="iwillgetjob@ttn";
    final String from="linksharing.noReply@gmail.com";

     static Boolean send(String to,String msg,String sub) throws UnsupportedEncodingException {
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);

            message.setFrom(new InternetAddress(from,"noReply@linkSharing"));
            message.setContent(msg,"text/html");
            Transport.send(message);
            System.out.println("message sent successfully");
            return true;
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }
}
