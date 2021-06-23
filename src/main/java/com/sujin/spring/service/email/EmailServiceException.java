package com.sujin.spring.service.email;

public class EmailServiceException extends Exception {

    EmailServiceException() {
        super("Error while composing email");
    }

    EmailServiceException(String message) {
        super(message);
    }

    EmailServiceException(Throwable throwable) {
        super(throwable);
    }

    EmailServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
