package ru.netology.moneytransferapplication.exception;

import lombok.Getter;

public class TransferException extends RuntimeException {
    @Getter
    private final String id;
    public TransferException(String message, String id) {
        super(message + " | id of post request: " + id);
        this.id = id;
    }
}
