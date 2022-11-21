package com.exam.notification.email.service;

import com.exam.notification.email.constant.TemplatePaths;
import com.exam.notification.email.helper.TemplateFinder;
import com.exam.notification.email.model.MailRequest;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@Service
public class EmailService {

    public enum EmailType {
        PASSWORD_RESET,
        PASSWORD_UPDATED
    }
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration configuration;

    public void sendSimpleMail(MailRequest request, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(request.getSender());
        message.setTo(request.getRecipient());
        message.setSubject(request.getSubject());
        message.setText(content);

        mailSender.send(message);
        System.out.println("Email sent successfully to "+ request.getRecipient());
    }

    public void sendTemplatedEmail(MailRequest request, Map<String, Object> model, EmailType emailType) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(request.getSender());
        mimeMessageHelper.setTo(request.getRecipient());
        mimeMessageHelper.setSubject(request.getSubject());

        Template template = configuration.getTemplate(TemplateFinder.findTemplatePathByEmailType(emailType));
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);


        mimeMessageHelper.setText(html, true);

        mailSender.send(mimeMessage);
        System.out.println("Email sent successfully to "+ request.getRecipient());
    }

    public void sendEmailWithAttachment(MailRequest request, String attachment, Map<String, Object> model, EmailType emailType)
            throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(request.getSender());
        mimeMessageHelper.setTo(request.getRecipient());
        mimeMessageHelper.setSubject(request.getSubject());

        Template template = configuration.getTemplate("password-reset.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        mimeMessageHelper.setText(html, true);

        if(attachment != null && !attachment.isEmpty()) {
            FileSystemResource fileSystem = new FileSystemResource(attachment);
            mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
        }

        mailSender.send(mimeMessage);
        System.out.println("Email with attachment sent successfully to "+ request.getRecipient());
    }
}
