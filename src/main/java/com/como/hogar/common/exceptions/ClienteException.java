package com.como.hogar.common.exceptions;

public class ClienteException  extends BaseException {
    public ClienteException() {
        super();
    }

    public ClienteException(String message) {
        super(message);
    }

    public ClienteException(String message, Throwable cause) {
        super(message, cause);
    }
}