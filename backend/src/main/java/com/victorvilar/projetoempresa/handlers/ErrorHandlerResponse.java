package com.victorvilar.projetoempresa.handlers;

public class ErrorHandlerResponse {

    private String status = "error";
    private String message;
    private int statusCode;


    public ErrorHandlerResponse( String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
