package ru.netology.moneytransferapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Operation {
    private String cardFromNumber;
    private String cardFromValidTill;
    private String cardFromCVV;
    private String cardToNumber;
    private Amount amount;

    @Override
    public String toString() {
        return String.format(" | Карта списания: %s | Карта зачисления: %s | Сумма: %s | Комиссия: %s",
                cardFromNumber, cardToNumber, amount.getValue(), amount.getValue() / 100);
    }
}
