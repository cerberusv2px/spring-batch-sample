package com.sujin.spring.service.email;

public interface EmailService {

    boolean sendAlert(EmailRequest emailRequest) throws EmailServiceException;

    boolean send(EmailRequest emailRequest) throws EmailServiceException;
}
