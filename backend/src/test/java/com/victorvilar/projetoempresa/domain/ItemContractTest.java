package com.victorvilar.projetoempresa.domain;

import com.victorvilar.projetoempresa.enums.MeasurementUnit;
import com.victorvilar.projetoempresa.enums.Schedule;
import com.victorvilar.projetoempresa.enums.Weekday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ItemContractTest {

    CollectionFrequency collectionFrequency1 = new CollectionFrequency();
    CollectionFrequency collectionFrequency2 = new CollectionFrequency();
    ItemContract itemContract1 = new ItemContract();
    ItemContract itemContract2 = new ItemContract();

    @BeforeEach
    private void setUp(){

        collectionFrequency1.setDays(Set.of(Weekday.SEGUNDA,Weekday.QUARTA,Weekday.SEXTA));
        collectionFrequency1.setSchedule("SEMANAL");


        collectionFrequency2.setDays(Set.of(Weekday.TERCA,Weekday.QUINTA,Weekday.SABADO));
        collectionFrequency2.setSchedule("SEMANAL");


        itemContract1.setCollectionFrequency(collectionFrequency1);
        itemContract2.setCollectionFrequency(collectionFrequency2);
    }

    @Test
    @DisplayName("Testing the frequency of the itemContract service")
    public void settingCollectionFrequency(){


        assertEquals(itemContract1.getCollectionFrequency(), collectionFrequency1);
        assertEquals(itemContract2.getCollectionFrequency(), collectionFrequency2);

    }


    @Test
    public void findWeekdaysInItemContractCollectionFrequency(){

        assertTrue(itemContract1.getCollectionFrequency().getDays().contains(Weekday.SEGUNDA));
        assertTrue(itemContract1.getCollectionFrequency().getDays().contains(Weekday.QUARTA));
        assertTrue(itemContract1.getCollectionFrequency().getDays().contains(Weekday.SEXTA));

        assertFalse(itemContract1.getCollectionFrequency().getDays().contains(Weekday.SABADO));
        assertFalse(itemContract1.getCollectionFrequency().getDays().contains(Weekday.DOMINGO));


        assertTrue(itemContract2.getCollectionFrequency().getDays().contains(Weekday.TERCA));
        assertTrue(itemContract2.getCollectionFrequency().getDays().contains(Weekday.QUINTA));
        assertTrue(itemContract2.getCollectionFrequency().getDays().contains(Weekday.SABADO));

        assertFalse(itemContract2.getCollectionFrequency().getDays().contains(Weekday.SEGUNDA));
        assertFalse(itemContract2.getCollectionFrequency().getDays().contains(Weekday.DOMINGO));

    }







}