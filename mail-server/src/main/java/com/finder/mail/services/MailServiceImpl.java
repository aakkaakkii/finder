package com.finder.mail.services;

import com.finder.mail.port.in.MailService;
import com.finder.mail.port.models.request.MailRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;
    @Value("${mail.from}")
    private String from;

    @Override
    public void sendMail(MailRequestModel mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(from);
        mailMessage.setTo(mail.emailTo);
        mailMessage.setSubject(mail.subject);
        mailMessage.setText(mail.message);

        mailSender.send(mailMessage);
    }

    @Override
    public void sendHtmlMail(MailRequestModel mail){
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try {
            helper = new MimeMessageHelper(mailMessage, true);

            helper.setFrom(from);
            helper.setTo(mail.emailTo);
            helper.setSubject(getSubject(mail.subject));
            helper.setText(mail.message, true);
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    private String buildMail(String message, String templateName){
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process(templateName, context);
    }

    private String getSubject(String templateName) {
        return "";
    }
}
