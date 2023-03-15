package ru.netology.moneytransferapplication.service;

import ru.netology.moneytransferapplication.model.Operation;
import ru.netology.moneytransferapplication.model.OperationConfirmation;

public interface TransferService {
    String transfer(Operation operation);

    String confirmOperation(OperationConfirmation operationConfirmation);
}
