package ru.netology.moneytransferapplication.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.netology.moneytransferapplication.model.Operation;
import ru.netology.moneytransferapplication.model.OperationConfirmation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransferRepositoryImpl implements TransferRepository {
    private volatile static int id = 0;
    private static final Logger log = Logger.getLogger(TransferRepositoryImpl.class);
    private final Map<Integer, Operation> operationRepository = new ConcurrentHashMap<>();

    public String save(Operation operation) {
        operationRepository.put(id, operation);
        log.info("Объект " + operation + " сохранен в репозиторий под id: " + id);
        return String.valueOf(id);
    }

    public String checkOperation(OperationConfirmation operationConfirmation) {
        operationConfirmation.setOperationId(String.valueOf(id++));
        return operationConfirmation.getOperationId();
    }
}
