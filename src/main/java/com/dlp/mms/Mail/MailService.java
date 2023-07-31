package com.dlp.mms.Mail;

public interface MailService {
    String sendMail(MailDetails mailDetails);
    String sendMailWithAttachment(MailDetails mailDetails);
}