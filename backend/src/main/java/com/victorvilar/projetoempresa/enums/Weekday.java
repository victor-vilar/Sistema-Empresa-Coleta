package com.victorvilar.projetoempresa.enums;

/**
 * weekdays in pt-br
 * @author Victor Vilar
 */
public enum Weekday {

    DOMINGO(1,"domingo"),
    SEGUNDA(2,"segunda-feira"),
    TERCA(3,"terça-feira"),
    QUARTA(4,"quarta-feira"),
    QUINTA(5,"quinta-feira"),
    SEXTA(6,"sexta-feira"),
    SABADO(7,"sábado");

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
