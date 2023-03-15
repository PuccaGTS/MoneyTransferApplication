package ru.netology.moneytransferapplication.service;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.netology.moneytransferapplication.model.Operation;
import ru.netology.moneytransferapplication.model.OperationConfirmation;
import ru.netology.moneytransferapplication.repository.TransferRepository;
import ru.netology.moneytransferapplication.validation.ServiceValidation;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    private final ServiceValidation serviceValidation = new ServiceValidation();
    private static final Logger log = Logger.getLogger(TransferServiceImpl.class);

    public String transfer(Operation operation) {
        String id = transferRepository.save(operation);
        serviceValidation.validTransferService(operation, id);
        log.info("Операция: " + operation + "сохранена под id: " + id + ". Ожидаается подтверждение.........");
        return id;
    }

    public String confirmOperation(OperationConfirmation operationConfirmation) {
        serviceValidation.validConfirmOperationService(operationConfirmation);
        log.info("Операция с id: " + operationConfirmation.getOperationId() + " подтверждена. Перевод выполнен успешно");
        return transferRepository.checkOperation(operationConfirmation);
    }
}
