package com.como.hogar.web.exceptions;

import com.como.hogar.common.exceptions.BaseException;

public class ParametroEntradaException extends BaseException {
    public ParametroEntradaException() {
        super();
    }

    public ParametroEntradaException(String message) {
        super(message);
    }

    public ParametroEntradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
