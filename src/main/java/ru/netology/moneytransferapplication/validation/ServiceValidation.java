package ru.netology.moneytransferapplication.validation;

import org.apache.log4j.Logger;
import ru.netology.moneytransferapplication.exception.TransferException;
import ru.netology.moneytransferapplication.exception.WrongInputException;
import ru.netology.moneytransferapplication.model.Operation;
import ru.netology.moneytransferapplication.model.OperationConfirmation;

public class ServiceValidation {
    private static final Logger log = Logger.getLogger(ServiceValidation.class);

    public void validTransferService(Operation operation, String id) {
        if (operation == null) {
            log.error("Ошибка операции: операция не определена");
            throw new TransferException("Transfer is not available", id);
        }
        if (operation.cardToNumber() == null) {
            log.error("Карта списания не заполнена");
            throw new WrongInputException("CardTo number is empty", id);
        }
        if (operation.cardFromNumber() == null) {
            log.error("Карта зачисления не заполнена");
            throw new WrongInputException("CardFrom number is empty", id);
        }
        if (operation.cardFromCVV() == null) {
            log.error("CVV не заполнен");
            throw new WrongInputException("Card CVV is empty", id);
        }
        if (operation.cardFromValidTill() == null) {
            log.error("Указана неверная дата карты списания");
            throw new WrongInputException("CardFromValidTill is empty", id);
        }
        if (operation.amount().value() == 0) {
            log.error("Не указана сумма списания");
            throw new WrongInputException("Money transfer value is 0", id);
        }
    }

    public void validConfirmOperationService(OperationConfirmation operationConfirmation) {
        if (operationConfirmation == null) {
            log.info("Ошибка подтверждения операции");
            throw new TransferException("Error transfer, confirmation is null", null);
        }
        String id = operationConfirmation.operationId();
        if (id == null) {
            log.error("Неправльный id операции");
            throw new WrongInputException("Wrong Input OperationId", null);
        }
        if (!(operationConfirmation.code().equals("0000"))) {
            log.error("Неправльный код подтвержедния операции");
            throw new WrongInputException("Bad code of Operation", id);
        }
    }
}
