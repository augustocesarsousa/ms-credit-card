package com.acsousa.creditcard.mscreditappraiser.application.exception;

public class ClientDataNotFoundException extends Exception{

    public ClientDataNotFoundException(String cpf) {
        super("Client data not found to the CPF " + cpf);
    }

    
}