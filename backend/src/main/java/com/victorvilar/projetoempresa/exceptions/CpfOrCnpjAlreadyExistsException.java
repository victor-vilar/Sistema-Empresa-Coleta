package com.victorvilar.projetoempresa.exceptions;

public class CpfOrCnpjAlreadyExistsException extends RuntimeException {
    public CpfOrCnpjAlreadyExistsException(String s) {
        super(s);
    }
}
