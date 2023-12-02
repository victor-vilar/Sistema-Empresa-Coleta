package com.victorvilar.projetoempresa.enums;

public enum MeasurementUnit {

    METRO_CUBICO(1),
    QUILOS(2),
    LITROS(3),
    EQUIPAMENTO(4);

    private final int id;

    MeasurementUnit(int id){
        this.id =id;
    }


    public int getId(){
        return this.id;
    }

    public static MeasurementUnit getById(Integer id){
        for(MeasurementUnit status : values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }

}
