package com.dlp.mms.Mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{
    @Autowired
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendMail(MailDetails mailDetails) {
        try {
            SimpleMailMessage emailMessage = new SimpleMailMessage();
            emailMessage.setFrom(sender);
            emailMessage.setTo(mailDetails.getRecipient());
            emailMessage.setSubject(mailDetails.getSubject());
            emailMessage.setText(mailDetails.getMessageBody());
            mailSender.send(emailMessage);
            return "Email has been sent successfully";
        } catch (Exception e){
            return "Error while sending email";
        }
    }

    @Override
    public String sendMailWithAttachment(MailDetails mailDetails) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(mailDetails.getRecipient());
            mimeMessageHelper.setSubject(mailDetails.getSubject());
            mimeMessageHelper.setText(mailDetails.getMessageBody());

            FileSystemResource file = new FileSystemResource(new File(mailDetails.getAttachment()));
            mimeMessageHelper.addAttachment(file.getFilename(), file);
            mailSender.send(mimeMessage);
            return "Email has been sent successfully";
        } catch (MessagingException e) {
            return "Error while sending email";
        }
    }
}
