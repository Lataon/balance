package com.gmail.elbaglikov.exceptions;

public class QueryServerException extends RuntimeException {
    public QueryServerException() {
    }

    public QueryServerException(String message) {
        super(message);
    }

    public QueryServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryServerException(Throwable cause) {
        super(cause);
    }

    public QueryServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
