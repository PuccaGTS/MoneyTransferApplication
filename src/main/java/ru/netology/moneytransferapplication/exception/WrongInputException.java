package ru.netology.moneytransferapplication.exception;

import lombok.Getter;

public class WrongInputException extends RuntimeException {
    @Getter
    private final String id;
    public WrongInputException(String message, String id) {
        super(message + " | id of post request: " + id);
        this.id = id;
    }
}
