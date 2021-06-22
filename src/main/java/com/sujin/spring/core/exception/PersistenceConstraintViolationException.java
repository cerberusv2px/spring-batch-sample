package com.sujin.spring.core.exception;

public class PersistenceConstraintViolationException extends Exception {

    PersistenceConstraintViolationException() {
        super(" does not satisfy the domain constraints");
    }

    PersistenceConstraintViolationException(String message) {
        super(message);
    }

    PersistenceConstraintViolationException(Throwable throwable) {
        super(throwable);
    }

    public PersistenceConstraintViolationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
