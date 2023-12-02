package com.victorvilar.projetoempresa.exceptions;

public class ApplicationUserNotFoundException extends RuntimeException {

    public ApplicationUserNotFoundException(String msg){
        super(msg);
    }

}
