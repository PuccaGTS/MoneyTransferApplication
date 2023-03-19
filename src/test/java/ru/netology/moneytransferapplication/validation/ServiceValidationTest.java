package ru.netology.moneytransferapplication.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import ru.netology.moneytransferapplication.exception.TransferException;
import ru.netology.moneytransferapplication.exception.WrongInputException;
import ru.netology.moneytransferapplication.model.Amount;
import ru.netology.moneytransferapplication.model.Operation;
import ru.netology.moneytransferapplication.model.OperationConfirmation;

import java.util.stream.Stream;

public class ServiceValidationTest {
    ServiceValidation serviceValidation;
    @BeforeEach
    public void initServiceValidation(){
        serviceValidation = new ServiceValidation();
    }
    @ParameterizedTest
    @MethodSource("nullOperationArguments")
    @DisplayName("Валидация операции, которая равна null, ожидается исключение TransferException")
    public void validOperationNullValue(Operation operation, String id){
        TransferException e = Assertions.assertThrows(TransferException.class, ()-> serviceValidation.validTransferService(operation, id));
        String expected = String.format("Transfer is not available | id of post request: %s", id);
        Assertions.assertEquals(e.getMessage(), expected);
    }
    @ParameterizedTest
    @MethodSource("nullCardToNumberArguments")
    @DisplayName("Валидация операции, в поле CardToNumber которой приходит null, ожидается исключение WrongInputException")
    public void validOperationCardToNumberNull(Operation operation, String id){
        WrongInputException e = Assertions.assertThrows(WrongInputException.class, ()-> serviceValidation.validTransferService(operation, id));
        String expected = String.format("CardTo number is empty | id of post request: %s", id);
        Assertions.assertEquals(e.getMessage(), expected);
    }
    @ParameterizedTest
    @MethodSource("nullCardFromNumberArguments")
    @DisplayName("Валидация операции, в поле CardFromNumber которой приходит null, ожидается исключение WrongInputException")
    public void validOperationCardFromNumberNull(Operation operation, String id){
        WrongInputException e = Assertions.assertThrows(WrongInputException.class, ()-> serviceValidation.validTransferService(operation, id));
        String expected = String.format("CardFrom number is empty | id of post request: %s", id);
        Assertions.assertEquals(e.getMessage(), expected);
    }
    @ParameterizedTest
    @MethodSource("nullCardCVVArguments")
    @DisplayName("Валидация операции, в поле CVV которой приходит null, ожидается исключение WrongInputException")
    public void validOperationCardCvvNull(Operation operation, String id){
        WrongInputException e = Assertions.assertThrows(WrongInputException.class, ()-> serviceValidation.validTransferService(operation, id));
        String expected = String.format("Card CVV is empty | id of post request: %s", id);
        Assertions.assertEquals(e.getMessage(), expected);
    }
    @ParameterizedTest
    @MethodSource("nullCardFromValidTillArguments")
    @DisplayName("Валидация операции, в поле cardFromValidTill которой приходит null, ожидается исключение WrongInputException")
    public void validOperationCardFromValidTillNull(Operation operation, String id){
        WrongInputException e = Assertions.assertThrows(WrongInputException.class, ()-> serviceValidation.validTransferService(operation, id));
        String expected = String.format("CardFromValidTill is empty | id of post request: %s", id);
        Assertions.assertEquals(e.getMessage(), expected);
    }
    @ParameterizedTest
    @MethodSource("nullCardAmountArguments")
    @DisplayName("Валидация операции, в поле Amount.value которой приходит null, ожидается исключение WrongInputException")
    public void validOperationCardAmountValueNull(Operation operation, String id){
        WrongInputException e = Assertions.assertThrows(WrongInputException.class, ()-> serviceValidation.validTransferService(operation, id));
        String expected = String.format("Money transfer value is 0 | id of post request: %s", id);
        Assertions.assertEquals(e.getMessage(), expected);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Валидация подтверждения операции, которая равна null, ожидается исключение TransferException")
    public void validOperationConfirmationNullValue(OperationConfirmation operationConfirmation){
        TransferException e = Assertions.assertThrows(TransferException.class, ()-> serviceValidation.validConfirmOperationService(operationConfirmation));
        String expected = String.format("Error transfer, confirmation is null | id of post request: %s", null);
        Assertions.assertEquals(e.getMessage(), expected);
    }
    @ParameterizedTest
    @MethodSource("nullIdOperationConfirmationArguments")
    @DisplayName("Валидация подтверждения операции, в поле OperationId которой приходит null, ожидается исключение WrongInputException")
    public void validOperationConfirmationNullId(OperationConfirmation operationConfirmation){
        WrongInputException e = Assertions.assertThrows(WrongInputException.class, ()-> serviceValidation.validConfirmOperationService(operationConfirmation));
        String expected = String.format("Wrong Input OperationId | id of post request: %s", null);
        Assertions.assertEquals(e.getMessage(), expected);
    }
    @ParameterizedTest
    @MethodSource("BadCodeOperationConfirmationArguments")
    @DisplayName("Валидация подтверждения операции, в поле code которой приходит null, ожидается исключение WrongInputException")
    public void validOperationConfirmationBadCode(OperationConfirmation operationConfirmation){
        WrongInputException e = Assertions.assertThrows(WrongInputException.class, ()-> serviceValidation.validConfirmOperationService(operationConfirmation));
        String expected = String.format("Bad code of Operation | id of post request: %s", operationConfirmation.operationId());
        Assertions.assertEquals(e.getMessage(), expected);
    }

    private static Stream<Arguments> nullOperationArguments(){
        return Stream.of(
                Arguments.of(null, "1"),
                Arguments.of(null, "10"),
                Arguments.of(null, "0")

        );
    }
    private static Stream<Arguments> nullCardToNumberArguments(){
        return Stream.of(
                Arguments.of(new Operation("1234123412341234", "1223", "123",null, new Amount(100, "rub")), "1"),
                Arguments.of(new Operation("4234543253252342", "3242", "143",null, new Amount(1000, "dol")), "2"),
                Arguments.of(new Operation("1235678679597655", "9765", "564",null, new Amount(10000, "rub")), "13")
        );
    }
    private static Stream<Arguments> nullCardFromNumberArguments(){
        return Stream.of(
                Arguments.of(new Operation(null, "1223", "123","1234123412341234", new Amount(100, "rub")), "1"),
                Arguments.of(new Operation(null, "3242", "143","4234543253252342", new Amount(1000, "dol")), "2"),
                Arguments.of(new Operation(null, "9765", "564","1235678679597655", new Amount(10000, "rub")), "13")
        );
    }
    private static Stream<Arguments> nullCardCVVArguments(){
        return Stream.of(
                Arguments.of(new Operation("4234543253252342", "1223", null,"1234123412341234", new Amount(100, "rub")), "1"),
                Arguments.of(new Operation("1235678679597655", "3242", null,"4234543253252342", new Amount(1000, "dol")), "2"),
                Arguments.of(new Operation("1234123412341234", "9765", null,"1235678679597655", new Amount(10000, "rub")), "13")
        );
    }
    private static Stream<Arguments> nullCardFromValidTillArguments(){
        return Stream.of(
                Arguments.of(new Operation("4234543253252342", null, "213","1234123412341234", new Amount(100, "rub")), "1"),
                Arguments.of(new Operation("1235678679597655", null, "543","4234543253252342", new Amount(1000, "dol")), "2"),
                Arguments.of(new Operation("1234123412341234", null, "213","1235678679597655", new Amount(10000, "rub")), "13")
        );
    }
    private static Stream<Arguments> nullCardAmountArguments(){
        return Stream.of(
                Arguments.of(new Operation("4234543253252342", "1233", "213","1234123412341234", new Amount(0, "rub")), "1"),
                Arguments.of(new Operation("1235678679597655", "2132", "543","4234543253252342", new Amount(0, "dol")), "2"),
                Arguments.of(new Operation("1234123412341234", "2131", "213","1235678679597655", new Amount(0, "rub")), "13")
        );
    }
    private static Stream<Arguments> nullIdOperationConfirmationArguments(){
        return Stream.of(
                Arguments.of(new OperationConfirmation(null, "0000")),
                Arguments.of(new OperationConfirmation(null, "1234")),
                Arguments.of(new OperationConfirmation(null, "4321"))
        );
    }
    private static Stream<Arguments> BadCodeOperationConfirmationArguments(){
        return Stream.of(
                Arguments.of(new OperationConfirmation("1", "2134")),
                Arguments.of(new OperationConfirmation("2", "1234")),
                Arguments.of(new OperationConfirmation("10", "4321"))
        );
    }
}
