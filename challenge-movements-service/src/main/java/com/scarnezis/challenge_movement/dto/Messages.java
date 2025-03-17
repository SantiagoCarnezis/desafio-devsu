package com.scarnezis.challenge_movement.dto;

public class Messages {

    // Successful Responses

    public static final String ACCOUNT_FOUND = "Accounts successfully found";
    public static final String ACCOUNT_CREATED = "Account created";
    public static final String ACCOUNT_DELETED = "Account deleted";

    public static final String ACCOUNT_TRANSACTIONS = "Account transactions";
    public static final String TRANSACTION_FOUND = "Transaction found";
    public static final String PAYMENT_COMPLETED = "Payment completed";

    public static final String REPORTS_FOUND = "Client's account status report";
    public static final String CLIENT_WITHOUT_ACCOUNTS = "The client does not have any accounts yet";
    public static final String ACCOUNT_WITHOUT_TRANSACTIONS = "The account does not have any transactions yet";

    // Errors
    public static final String ACCOUNT_NOT_FOUND = "Account not found with number ";
    public static final String INSUFFICIENT_BALANCE = "Insufficient balance in account ";
    public static final String CLOSED_ACCOUNT_MAKE_PAYMENT = "Cannot make a payment with a closed account";
    public static final String CLOSED_ACCOUNT_RECEIVE_PAYMENT = "Cannot receive a payment in a closed account";

    public static String INVALID_BODY = "Invalid request body";
}
