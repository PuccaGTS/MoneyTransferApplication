package ru.netology.moneytransferapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OperationConfirmation {
    private String operationId;
    private String code;

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
    @Override
    public String toString() {
        return "OperationConfirmation{" +
                "operationId='" + operationId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
