package com.tryingpfq.identifier;

public class IdGeneratorFailureException extends Exception {

    public IdGeneratorFailureException(String message, Exception e) {
        super(message);
    }
}
