package com.victorvilar.projetoempresa.domain;

import com.victorvilar.projetoempresa.enums.Schedule;
import com.victorvilar.projetoempresa.enums.Weekday;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class that represents the frequency of collection of an item. Itens have different needs of collection.
 * For instance an itemContract service must have to be execute every monday, tuesday and friday, or may be every saturday once a week, or
 * once a month on a wednesday. So, this class represent these infinite possibilities that may exist
 * It has a list of days, that can be from sunday to saturday
 * also has a frequency, it will be daily, weekly, monthly  etc.?
 * @author Victor Vilar
 *
 */
@Entity
public class CollectionFrequency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name="collection_frequency_days")
    @Column(name="days")
    private Set<Integer> days = new HashSet<>();
    private Integer schedule;


    public CollectionFrequency() {
    }
    public CollectionFrequency(Long id, Set<Weekday> days, String schedule) {
        this.id = id;
        this.setDays(days);
        this.setSchedule(schedule);
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public void setDays(Set<Weekday> days) {
        this.days = days.stream().map(day -> day.getId()).collect(Collectors.toSet());
    }

    public void addNewDay(Weekday day){
        this.days.add(day.getId());
    }

    public void removeDay(Weekday day){
        if(this.days.contains(day.getId())) this.days.remove(day.getId());
    }

    public Set<Weekday> getDays() {
        return this.days.stream().map(day -> Weekday.getByDay(day)).collect(Collectors.toSet());
    }

    public void setSchedule(String schedule) {
        this.schedule = Schedule.getByName(schedule).getId();
    }

    public String getSchedule() {
        return Schedule.getById(schedule).getName();
    }




    @Override
    public String toString() {
        return "Dias de Coleta:" + this.getDays() + " - Frequência: " + this.getSchedule();

    }
}
