package com.dlp.mms.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    //Sending email
    @PostMapping("/send-mail")
    public String sendMail(@RequestBody MailDetails mailDetails) {
        return mailService.sendMail(mailDetails);
    }
    //Sending email with attachment
    @PostMapping("/send-mail-attachment")
    public String sendMailWithAttachment(@RequestBody MailDetails mailDetails)
    {
        return mailService.sendMailWithAttachment(mailDetails);
    }
}