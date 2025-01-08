package com.victorvilar.projetoempresa.dto.contract;

import com.victorvilar.projetoempresa.enums.Schedule;
import com.victorvilar.projetoempresa.enums.Weekday;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionFrequencyDto {

    private Long id;
    private Set<Integer> days = new HashSet<>();
    private Integer schedule;


    public void setDays(Set<Weekday> days) {
        this.days = days.stream().map(day -> day.getId()).collect(Collectors.toSet());
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

}
