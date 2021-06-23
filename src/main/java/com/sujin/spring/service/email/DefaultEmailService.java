package com.sujin.spring.service.email;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// @Component
@Slf4j
public class DefaultEmailService implements EmailService {

    private final JavaMailSender mailSender;

    private final InternetAddress alertEmail;
    private final InternetAddress systemEmail;

    public DefaultEmailService(
        JavaMailSender mailSender) throws UnsupportedEncodingException {
        this.mailSender = mailSender;

        alertEmail = new InternetAddress("alert-cloudlake@rosia.one", "CloudLake Alert");
        systemEmail = new InternetAddress("no-reply-cloudlake@rosia.one", "CloudLake");
    }

    @Override
    public boolean sendAlert(EmailRequest emailRequest) throws EmailServiceException {
        final MimeMessage message = mailSender.createMimeMessage();

        try {

            final MimeMessageHelper helper = new MimeMessageHelper(message,
                CollectionUtils.isNotEmpty(emailRequest.getAttachments()));

            helper.setFrom(alertEmail.toString());
            helper.setTo(emailRequest.getTo());

            if (CollectionUtils.isNotEmpty(emailRequest.getCc())) {
                helper.setCc(emailRequest.getCc().toArray(new String[0]));
            }

            helper.setSubject(emailRequest.getSubject());
            helper.setValidateAddresses(true);

            helper.setText(emailRequest.getMessage(), true);

            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("Error while composing email {}", emailRequest, e);
            throw new EmailServiceException(e.getLocalizedMessage(), e);
        }

        return true;
    }

    @Override
    public boolean send(EmailRequest emailRequest) throws EmailServiceException {

        final MimeMessage message = mailSender.createMimeMessage();
        try {

            final MimeMessageHelper helper = new MimeMessageHelper(message,
                CollectionUtils.isNotEmpty(emailRequest.getAttachments()));

            helper.setFrom(systemEmail.toString());
            helper.setTo(emailRequest.getTo());

            helper.setSubject(emailRequest.getSubject());
            helper.setValidateAddresses(true);

            helper.setText(emailRequest.getMessage());

            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("Error while composing email {}", emailRequest, e);
            throw new EmailServiceException(e.getLocalizedMessage(), e);
        }

        return true;
    }
}
