package com.victorvilar.projetoempresa.enums;

public enum FuelType {

    GASOLINA_COMUM("Gasolina Comun"),
    GASOLINA_ADITIVADA("Gasolina Aditivada"),
    ALCOOL("Alcool"),
    DIESEL_S500("Diesel S500"),
    DIESEL_S10("Dielse S10"),
    GAS("Gás");

    private String type;
    FuelType(String type){
        this.type =type;
    }

    public String getType(){
        return this.type;
    }
}
