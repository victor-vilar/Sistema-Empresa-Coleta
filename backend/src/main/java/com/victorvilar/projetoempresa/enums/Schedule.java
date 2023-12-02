package com.victorvilar.projetoempresa.enums;

/**
 * enum that represents a schedule to collect an ItemContract
 * @author Victor Vilar
 */
public enum Schedule {
    DIARIO(1,"diário"),
    SEMANAL(2,"semanal"),
    QUINZENAL(3,"quinzenal"),
    MENSAL(4,"mensal"),
    SOB_SOLICITACAO(5,"sob-solicitação");

    private final int id;
    private final String schedule;

    Schedule(int id, String schedule){
        this.id = id;
        this.schedule = schedule;
    }


    public int getId() {
        return id;
    }
    public String getSchedule() {
        return schedule;
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
            if(s.getSchedule().equals(name)){
                return s;
            }
        }
        return null;
    }

}
