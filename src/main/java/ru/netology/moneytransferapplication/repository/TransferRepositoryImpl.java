package ru.netology.moneytransferapplication.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.netology.moneytransferapplication.model.Operation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TransferRepositoryImpl implements TransferRepository {
    private AtomicInteger id = new AtomicInteger(0);
    private static final Logger log = Logger.getLogger(TransferRepositoryImpl.class);
    private final Map<Integer, Operation> operationRepository = new ConcurrentHashMap<>();

    public String save(Operation operation) {
        operationRepository.put(id.get(), operation);
        log.info("Объект " + operation + " сохранен в репозиторий под id: " + id.get());
        return String.valueOf(id.getAndIncrement());
    }
}
