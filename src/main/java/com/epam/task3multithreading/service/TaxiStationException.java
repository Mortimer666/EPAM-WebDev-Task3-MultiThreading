package com.epam.task3multithreading.service;

public class TaxiStationException extends RuntimeException {
    public TaxiStationException() {
    }

    public TaxiStationException(String message) {
        super(message);
    }

    public TaxiStationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaxiStationException(Throwable cause) {
        super(cause);
    }

    public TaxiStationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
