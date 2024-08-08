package com.victorvilar.projetoempresa.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test of weekday constant
 */
class WeekdayTest {

    @Test
    @DisplayName("find the weekday constant when passing the weekday id")
    public void findWeekdaySucessfully_WhenPassingValidDayId(){

        Weekday segunda = Weekday.getByDay(2);
        Weekday terca = Weekday.getByDay(3);
        assertEquals(segunda,Weekday.SEGUNDA);
        assertEquals(terca,Weekday.TERCA);


    }

    @Test
    @DisplayName("find the weekday constant when passing the weekday day name")
    public void findWeekdaySucessfully_WhenPassingValidDayName(){

        Weekday segunda = Weekday.getByName("segunda-feira");
        Weekday terca = Weekday.getByName("TERÇA-FEIRA");
        assertEquals(segunda,Weekday.SEGUNDA);
        assertEquals(terca,Weekday.TERCA);


    }







}