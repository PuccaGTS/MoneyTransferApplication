package ru.netology.moneytransferapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneytransferapplication.model.Operation;
import ru.netology.moneytransferapplication.model.OperationConfirmation;
import ru.netology.moneytransferapplication.model.Success200OK;
import ru.netology.moneytransferapplication.service.TransferServiceImpl;

@RestController
@RequestMapping
@CrossOrigin(origins = "${origins.host}")
@RequiredArgsConstructor
public class TransferController {
    private final TransferServiceImpl transferServiceImpl;

    @PostMapping("transfer")
    public ResponseEntity<Success200OK> transfer(@RequestBody Operation operation) {
        return ResponseEntity.ok().body(new Success200OK(transferServiceImpl.transfer(operation)));
    }

    @PostMapping("confirmOperation")
    public ResponseEntity<Success200OK> confirmOperation(@RequestBody OperationConfirmation operationConfirmation) {
        return ResponseEntity.ok().body(new Success200OK(transferServiceImpl.confirmOperation(operationConfirmation)));
    }
}
