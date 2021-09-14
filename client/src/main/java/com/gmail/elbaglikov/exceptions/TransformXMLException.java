package com.gmail.elbaglikov.exceptions;

public class TransformXMLException extends RuntimeException {
    public TransformXMLException() {
    }

    public TransformXMLException(String message) {
        super(message);
    }

    public TransformXMLException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransformXMLException(Throwable cause) {
        super(cause);
    }

    public TransformXMLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
