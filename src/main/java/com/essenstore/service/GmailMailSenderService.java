package com.essenstore.service;

import com.essenstore.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;

@Service
public class GmailMailSenderService extends EmailService {

    @Autowired
    public GmailMailSenderService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        super(mailSender, templateEngine);
    }

    @Async
    @Override
    public void send(Mail mail) {
        try {
            mailSender.send(getMimeMessage(mail));
        } catch (MessagingException e) {
            e.getStackTrace();
        }
    }
}
