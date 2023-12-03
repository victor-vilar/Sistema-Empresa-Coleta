package com.victorvilar.projetoempresa.enums;

public enum VehicleType {

    CAMINHAO_COMPACTADOR("Caminhão Compactador"),
    CAMINHAO_POLIGUINDASTE("Caminhão Poliguindaste"),
    CAMINHAO_ROLLON_ROLLOFF("Caminhão RollOn RollOff"),
    CAMINHAO_BAU("Caminhão Báu"),
    CAMINHAO_TANQUE("Caminhão Tanque"),
    FIORINO_FURGAO("Fiorino Furgão");

    private String type;

    VehicleType(String type){
        this.type=type;
    }

    public String getType(){
        return this.type;
    }


}
