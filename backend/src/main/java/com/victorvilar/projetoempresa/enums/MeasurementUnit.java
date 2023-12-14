package com.victorvilar.projetoempresa.enums;

public enum MeasurementUnit {

    METRO_CUBICO(1, "METRO CÚBICO"),
    QUILOS(2, "QUILOS"),
    LITROS(3,"LITROS"),
    EQUIPAMENTO(4,"EQUIPAMENTO");

    private final int id;
    private final String name;

    MeasurementUnit(int id, String name){

        this.id =id;
        this.name = name;
    }


    public int getId(){
        return this.id;
    }
    public String getName(){return this.name;}

    public static MeasurementUnit getById(Integer id){
        for(MeasurementUnit status : values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }


    public static MeasurementUnit getByName(String name ){
        for(MeasurementUnit measurementUnit : values()){
            if(measurementUnit.getName().equals(name)){
                return measurementUnit;
            }
        }
        return null;
    }

}
