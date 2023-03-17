package ru.netology.moneytransferapplication.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.ResponseEntity;
import ru.netology.moneytransferapplication.exception.TransferException;
import ru.netology.moneytransferapplication.exception.WrongInputException;

import java.util.stream.Stream;

public class GlobalExceptionHandlerTest {
    GlobalExceptionHandler globalExceptionHandler;
    @BeforeEach
    public void init_exception_handler(){
        globalExceptionHandler = new GlobalExceptionHandler();
    }
    @ParameterizedTest
    @MethodSource("transferExceptionHandlerArguments")
    public void transferExceptionHandlerTest(TransferException e){
        ResponseEntity responseEntity = globalExceptionHandler.transferExceptionHandler(e);
        String result = responseEntity.getStatusCode().toString();
        String expected = "500 INTERNAL_SERVER_ERROR";
        Assertions.assertEquals(expected, result);
    }
    @ParameterizedTest
    @MethodSource("wrongInputExceptionHandlerArguments")
    public void wrongInputExceptionTest(WrongInputException e){
        ResponseEntity responseEntity = globalExceptionHandler.wrongInputExceptionHandler(e);
        String result = responseEntity.getStatusCode().toString();
        String expected = "400 BAD_REQUEST";
        Assertions.assertEquals(expected, result);
    }
    private static Stream<Arguments> transferExceptionHandlerArguments(){
        return Stream.of(
                Arguments.of(new TransferException("test message", "10"))
        );
    }
    private static Stream<Arguments> wrongInputExceptionHandlerArguments(){
        return Stream.of(
                Arguments.of(new WrongInputException("test message", "10"))
        );
    }
}
