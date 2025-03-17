package com.scarnezis.challenge_user.dto;

public class Messages {

    // Successful Responses
    public static final String CLIENTS_FOUND = "Clients successfully found";
    public static final String CLIENT_FOUND = "Client successfully found";
    public static final String CLIENT_FOUND_WITHOUT_ACCOUNTS = "Client without accounts successfully found";
    public static final String CLIENT_CREATED = "Client successfully created";
    public static final String CLIENT_UPDATED = "Client successfully updated";
    public static final String CLIENT_DELETED = "Client successfully deleted";
    public static final String ACCOUNT_CLOSED = "Account closed";

    // Errors
    public static final String CLIENT_NOT_FOUND = "Client not found ";
    public static final String ACCOUNT_SERVICE_CONNECTION_ERROR = "Failed to connect with the account microservice. Could not retrieve client accounts ";
    public static final String ACCOUNTS_NOT_RETRIEVED = "Could not retrieve client accounts ";
    public static final String ERROR_RETRIEVING_ACCOUNTS = "An error occurred while parsing client accounts ";
}
