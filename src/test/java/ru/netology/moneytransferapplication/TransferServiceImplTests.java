package ru.netology.moneytransferapplication;

import org.junit.jupiter.api.AfterEach;
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
import ru.netology.moneytransferapplication.service.TransferServiceImpl;

import java.util.stream.Stream;

public class TransferServiceImplTests {
    TransferRepositoryImpl transferRepositoryImpl;
    @BeforeEach
    void init_service(){
        transferRepositoryImpl = Mockito.mock(TransferRepositoryImpl.class);
    }
    @AfterEach
    void destroy_service(){
        transferRepositoryImpl = null;
    }
    @MethodSource("source_1")
    @ParameterizedTest
    void test_transferService_transferMethod(Operation operation){
        Mockito.when(transferRepositoryImpl.save(operation)).thenReturn("1");
        TransferServiceImpl transferServiceImpl = new TransferServiceImpl(transferRepositoryImpl);

        String result = transferServiceImpl.transfer(operation);
        String expected = "1";

        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> source_1(){
        return Stream.of(Arguments.of(
                new Operation("1234123412341234", "12/24", "234", "4321432143214321", new Amount(1000, "rub"))));
    }

    @MethodSource("source_2")
    @ParameterizedTest
    void test_transferService_confirmOperationMethod(OperationConfirmation operationConfirmation){
        Mockito.when(transferRepositoryImpl.checkOperation(operationConfirmation)).thenReturn("10");
        TransferServiceImpl transferServiceImpl = new TransferServiceImpl(transferRepositoryImpl);

        String result = transferServiceImpl.confirmOperation(operationConfirmation);
        String expected = "10";

        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> source_2(){
        return Stream.of(Arguments.of(
                new OperationConfirmation("10", "0000")));
    }

    @MethodSource("source_3")
    @ParameterizedTest
    void test_transferService_confirmOperationMethod_wrongInputException(OperationConfirmation operationConfirmation) {
        Mockito.when(transferRepositoryImpl.checkOperation(operationConfirmation)).thenReturn("10");
        TransferServiceImpl transferServiceImpl = new TransferServiceImpl(transferRepositoryImpl);

        WrongInputException e = Assertions.assertThrows(WrongInputException.class, ()-> transferServiceImpl.confirmOperation(operationConfirmation));
        String expected = "Bad code of Operation | id of post request: 10";
        Assertions.assertEquals(e.getMessage(), expected);
    }
    public static Stream<Arguments> source_3(){
        return Stream.of(Arguments.of(
                new OperationConfirmation("10", "1111")));
    }
}
