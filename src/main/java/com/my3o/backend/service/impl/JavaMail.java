package com.my3o.backend.service.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;

import com.my3o.common.service.task.AbstractBaseDaemonBackgroundTask;

public class JavaMail extends AbstractBaseDaemonBackgroundTask {

    @Override
    public void run() {
        String SMTP_AUTH_USER = "anton.kovtun161@gmail.com";
        String SMTP_AUTH_PWD = "201310ADAAD";
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", SMTP_AUTH_USER);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.sendpartial", "true");
        Session session = Session.getDefaultInstance(props);
        try {
            session.setDebug(true);
            Transport transport = session.getTransport();
            transport.connect("smtp.gmail.com", 465, SMTP_AUTH_USER, SMTP_AUTH_PWD);
            MimeMessage message = new MimeMessage(session);
            message.setSubject("тестовое письмо!");
            message.setText("Тестирование JavaMail c Gmail почтой прошло успешно!");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("anton.kovtun93@gmail.com"));
            message.setSentDate(new Date());
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String POP_AUTH_USER = "anton.kovtun161@gmail.com";
        String POP_AUTH_PWD = "201310ADAAD";
        String FOLDER_INDOX = "INBOX"; // имя папки "Входящие"
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties pop3Props = new Properties();
        pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
        pop3Props.setProperty("mail.pop3.port", "995");
        pop3Props.setProperty("mail.pop3.socketFactory.port", "995");
        URLName url = new URLName("pop3", "pop.gmail.com", 955, "", POP_AUTH_USER, POP_AUTH_PWD);
        Session session1 = Session.getInstance(pop3Props, null);
        Store store;
        try {
            store = session1.getStore(url);

            store.connect();
            Folder folder = store.getFolder(FOLDER_INDOX);
            try {
                folder.open(Folder.READ_WRITE);
            } catch (MessagingException ex) {
                folder.open(Folder.READ_ONLY);
            }
            Message[] messages = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            // обработка сообщений
            folder.close(false);
            store.close();

        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
