package com.victorvilar.projetoempresa.enums;

/**
 * enum that represents a schedule to collect an ItemContract
 * @author Victor Vilar
 */
public enum Schedule {
    DIARIO(1,"DIÁRIO"),
    SEMANAL(2,"SEMANAL"),
    QUINZENAL(3,"QUINZENAL"),
    MENSAL(4,"MENSAL"),
    SOB_SOLICITACAO(5,"SOB SOLICITAÇÃO");

    private final int id;
    private final String name;

    Schedule(int id, String name){
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }


    public static Schedule getById(int id){
        for(Schedule s : Schedule.values()){
            if(s.id == id){
                return s;
            }
        }
        return null;
    }

    public static Schedule getByName(String name){
        for(Schedule s : Schedule.values()){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }

}
