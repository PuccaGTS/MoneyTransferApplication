package ru.netology.moneytransferapplication.model;

public record Operation(String cardFromNumber,String cardFromValidTill,String cardFromCVV,String cardToNumber,Amount amount) {
}