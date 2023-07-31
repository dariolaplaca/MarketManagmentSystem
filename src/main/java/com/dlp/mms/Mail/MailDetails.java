package com.dlp.mms.Mail;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailDetails {
    private String recipient;
    private String subject;
    private String messageBody;
    private String attachment;
}
