package com.victorvilar.projetoempresa.enums;

/**
 * weekdays in pt-br
 * @author Victor Vilar
 */
public enum Weekday {

    DOMINGO(1,"DOMINGO"),
    SEGUNDA(2,"SEGUNDA-FEIRA"),
    TERCA(3,"TERÇA-FEIRA"),
    QUARTA(4,"QUARTA-FEIRA"),
    QUINTA(5,"QUINTA-FEIRA"),
    SEXTA(6,"SEXTA-FEIRA"),
    SABADO(7,"SÁBADO");

    private final int id;
    private final String dayName;

    Weekday(int id, String dayName) {
        this.id = id;
        this.dayName=dayName;
    }

    public int getId(){
        return this.id;
    }

    public String getDayName(){
        return this.dayName;
    }



    public static Weekday getByName(String name) {
        for(Weekday weekDayConstant : values()){
            if( weekDayConstant.dayName.equals(name)){
                return weekDayConstant;
            }
        }
        return null;
    }


    public static Weekday getByDay(int id) {
        for(Weekday weekDayConstant : values()){
            if( weekDayConstant.id == id){
                return weekDayConstant;
            }
        }
        return null;
    }



}
