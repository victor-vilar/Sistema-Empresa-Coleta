package com.victorvilar.projetoempresa.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {

    @Test
    @DisplayName("find the schedule by schedule id")
    void findScheduleSucessfully_WhenPassingValidDayId(){

        Schedule diario = Schedule.getById(1);
        Schedule semanal = Schedule.getById(2);

        assertEquals(diario, Schedule.DIARIO);
        assertEquals(semanal, Schedule.SEMANAL);


    }

    @Test
    @DisplayName("find the schedule by schedule name")
    void  findScheduleSucessfully_WhenPassingValidDayName() {

        Schedule diario = Schedule.getByName("diário");
        Schedule semanal = Schedule.getByName("semanal");

        assertEquals(diario, Schedule.DIARIO);
        assertEquals(semanal, Schedule.SEMANAL);

    }

}