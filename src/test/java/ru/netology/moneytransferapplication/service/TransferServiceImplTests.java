package ru.netology.moneytransferapplication.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.moneytransferapplication.exception.WrongInputException;
import ru.netology.moneytransferapplication.model.Amount;
import ru.netology.moneytransferapplication.model.Operation;
import ru.netology.moneytransferapplication.model.OperationConfirmation;
import ru.netology.moneytransferapplication.repository.TransferRepositoryImpl;

import java.util.stream.Stream;

public class TransferServiceImplTests {
    TransferRepositoryImpl transferRepositoryImpl;
    @BeforeEach
    void init_service(){
        transferRepositoryImpl = Mockito.mock(TransferRepositoryImpl.class);
    }
    @MethodSource("transferMethodArguments")
    @ParameterizedTest
    void test_transferService_transferMethod(Operation operation){
        Mockito.when(transferRepositoryImpl.save(operation)).thenReturn("1");
        TransferServiceImpl transferServiceImpl = new TransferServiceImpl(transferRepositoryImpl);

        String result = transferServiceImpl.transfer(operation);
        String expected = "1";

        Assertions.assertEquals(expected, result);
    }

    @MethodSource("confirmOperationMethodArguments")
    @ParameterizedTest
    void test_transferService_confirmOperationMethod(OperationConfirmation operationConfirmation){
        TransferServiceImpl transferServiceImpl = new TransferServiceImpl(transferRepositoryImpl);
        String result = transferServiceImpl.confirmOperation(operationConfirmation);
        String expected = "10";

        Assertions.assertEquals(expected, result);
    }

    @MethodSource("confirmOperationMethodWrongInputExceptionArguments")
    @ParameterizedTest
    void test_transferService_confirmOperationMethod_wrongInputException(OperationConfirmation operationConfirmation) {
        TransferServiceImpl transferServiceImpl = new TransferServiceImpl(transferRepositoryImpl);

        WrongInputException e = Assertions.assertThrows(WrongInputException.class, ()-> transferServiceImpl.confirmOperation(operationConfirmation));
        String expected = "Bad code of Operation | id of post request: 10";
        Assertions.assertEquals(e.getMessage(), expected);
    }
    public static Stream<Arguments> transferMethodArguments(){
        return Stream.of(Arguments.of(
                new Operation("1234123412341234", "12/24", "234", "4321432143214321", new Amount(1000, "rub"))));
    }
    public static Stream<Arguments> confirmOperationMethodArguments(){
        return Stream.of(Arguments.of(
                new OperationConfirmation("10", "0000")));
    }
    public static Stream<Arguments> confirmOperationMethodWrongInputExceptionArguments(){
        return Stream.of(Arguments.of(
                new OperationConfirmation("10", "1111")));
    }
}
