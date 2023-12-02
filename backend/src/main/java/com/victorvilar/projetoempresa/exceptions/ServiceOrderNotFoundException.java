package com.victorvilar.projetoempresa.exceptions;

public class ServiceOrderNotFoundException extends RuntimeException {
    public ServiceOrderNotFoundException(String s) {
        super(s);
    }
}
