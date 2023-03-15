package ru.netology.moneytransferapplication.repository;

import ru.netology.moneytransferapplication.model.Operation;
import ru.netology.moneytransferapplication.model.OperationConfirmation;

public interface TransferRepository {
    String save(Operation operation);

    String checkOperation(OperationConfirmation operationConfirmation);
}
