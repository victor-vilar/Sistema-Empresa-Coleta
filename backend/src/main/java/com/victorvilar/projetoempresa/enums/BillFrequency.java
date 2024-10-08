package com.victorvilar.projetoempresa.enums;

public enum BillFrequency {

    ANUAL("Anual"),
    MENSAL("Mensal"),
    QUINZENAL("Quinzenal"),
    SEMANAL("Semanal"),
    DIARIO("Diario");

    private String frequency;

    BillFrequency(String frequency){
        this.frequency = frequency;
    }

    public String getFrequency(){
        return this.frequency;
    }
}
