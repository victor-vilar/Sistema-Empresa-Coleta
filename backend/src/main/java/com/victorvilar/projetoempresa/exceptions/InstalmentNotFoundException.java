package com.victorvilar.projetoempresa.exceptions;

public class InstalmentNotFoundException extends RuntimeException {
    public InstalmentNotFoundException(String msg) {
        super(msg);
    }
}
