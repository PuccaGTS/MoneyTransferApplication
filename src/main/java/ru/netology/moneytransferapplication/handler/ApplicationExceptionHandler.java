package ru.netology.moneytransferapplication.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.moneytransferapplication.exception.TransferException;
import ru.netology.moneytransferapplication.exception.WrongInputException;
import ru.netology.moneytransferapplication.model.UnSucccessResp;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(TransferException.class)
    public ResponseEntity<UnSucccessResp> transferExceptionHandler(TransferException e) {
        return new ResponseEntity<>(new UnSucccessResp(e.getMessage(), e.getId()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WrongInputException.class)
    public ResponseEntity<UnSucccessResp> wrongInputExceptionHandler(WrongInputException e) {
        return new ResponseEntity<>(new UnSucccessResp(e.getMessage(), e.getId()), HttpStatus.BAD_REQUEST);
    }
}
