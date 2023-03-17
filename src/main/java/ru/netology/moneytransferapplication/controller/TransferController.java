package ru.netology.moneytransferapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneytransferapplication.model.Operation;
import ru.netology.moneytransferapplication.model.OperationConfirmation;
import ru.netology.moneytransferapplication.model.SuccessTransferDto;
import ru.netology.moneytransferapplication.service.TransferServiceImpl;

@RestController
@RequestMapping
@CrossOrigin(origins = "${origins.host}")
@RequiredArgsConstructor
public class TransferController {
    private final TransferServiceImpl transferServiceImpl;

    @PostMapping("transfer")
    public ResponseEntity<SuccessTransferDto> transfer(@RequestBody Operation operation) {
        return ResponseEntity.ok().body(new SuccessTransferDto(transferServiceImpl.transfer(operation)));
    }

    @PostMapping("confirmOperation")
    public ResponseEntity<SuccessTransferDto> confirmOperation(@RequestBody OperationConfirmation operationConfirmation) {
        return ResponseEntity.ok().body(new SuccessTransferDto(transferServiceImpl.confirmOperation(operationConfirmation)));
    }
}
