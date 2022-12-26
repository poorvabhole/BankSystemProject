package com.cognologix.banksystem.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter@AllArgsConstructor
public enum ErrorEnum {

    CUSTOMER_NOT_FOUND("901","Customer with given Id not found"),

    EMPTY_LIST("902","Customer information is empty"),

    ACCOUNT_NOT_FOUND("903","Account with given number not found"),
    NOACCOUNT_FOR_CUSTOMER("904","Account for given customer Id is not present"),

    INSUFFICENT_BALANCE("904","Insufficent balance, can not withdraw money");
    private String code;
    private String message;

}
