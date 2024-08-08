package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.Bill;
import com.victorvilar.projetoempresa.domain.Instalment;
import com.victorvilar.projetoempresa.dto.bill.*;
import com.victorvilar.projetoempresa.dto.bill.interfaces.BillDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BillMapperTest {

    @Autowired
    BillMapper mapper;

    Bill bill1;
    Bill bill2;
    BillCreateDto billCreateDto1;
    BillCreateDto billCreateDto2;
    BillUpdateDto billUpdateDto1;
    BillUpdateDto billUpdateDto2;
    BillResponseDto billResponseDto1;
    BillResponseDto billResponseDto2;
    Instalment instalment1;
    Instalment instalment2;

    @BeforeEach
    void setUp(){

        bill1 = new Bill();
        bill2 = new Bill();
        instalment1 = new Instalment(1L,bill1, LocalDate.now(), BigDecimal.valueOf(10),LocalDate.now(),BigDecimal.valueOf(10),"/");
        instalment2 = new Instalment(2L,bill1, LocalDate.now(), BigDecimal.valueOf(10),LocalDate.now(),BigDecimal.valueOf(10),"/");


        bill1.setId(1L);
        bill1.setSupplier("Supplier1");
        bill1.setNoteNumber("111");
        bill1.setDescription("conta1");
        bill1.addNewInstalment(instalment1);

        bill2.setId(2L);
        bill2.setSupplier("Supplier2");
        bill2.setNoteNumber("222");
        bill2.setDescription("conta2");
        bill2.addNewInstalment(instalment2);

        billCreateDto1 = new BillCreateDto("Supplier1","111","conta1", List.of());
        billCreateDto2 = new BillCreateDto("Supplier2","222","conta2", List.of());
        billUpdateDto1 = new BillUpdateDto(1L,"Supplier1","111","conta1", List.of());
        billUpdateDto2 = new BillUpdateDto(2L,"Supplier2","222","conta2", List.of());


    }

    @Test
    void toBill_WhenPassingCreateDto() {
        Bill bill = this.mapper.toBill(billCreateDto1);
        compare(billCreateDto1,bill);
    }

    @Test
    void toBill_WhenPassingUpdateDto() {
        Bill bill = this.mapper.toBill(billUpdateDto1);
        assertEquals(bill.getId(), billUpdateDto1.getId());
        compare(billUpdateDto1,bill);
    }

    @Test
    void toBillList_WhenPassingListOfCreateDto() {
        List<Bill> bills = this.mapper.toBillList(List.of(billCreateDto1,billCreateDto2));
        List<BillCreateDto> billCreateDtos = List.of(billCreateDto1,billCreateDto2);
        for(int i = 0; i < bills.size(); i++){
            compare(billCreateDtos.get(i),bills.get(i));
        }
    }

    @Test
    void toBillList_WhenPassingListOfUpdateDto() {
        List<Bill> bills = this.mapper.toBillList(List.of(billUpdateDto1,billUpdateDto2));
        List<BillUpdateDto> billUpdateDtos = List.of(billUpdateDto1,billUpdateDto2);
        for(int i = 0; i < bills.size(); i++){
            assertEquals(billUpdateDtos.get(i).getId(),bills.get(i).getId());
            compare(billUpdateDtos.get(i),bills.get(i));
        }
    }

    @Test
    void toBillResponseDto() {
        BillResponseDto billResponseDto = this.mapper.toBillResponseDto(bill1);
        assertEquals(billResponseDto.getId(),bill1.getId());
        assertEquals(billResponseDto.getInstalments().get(0).getClass(), InstalmentResponseDto.class);
        compare(billResponseDto,bill1);
    }

    @Test
    void toBillResponseDtoList() {
        List<BillResponseDto> billsResponseDto = this.mapper.toBillResponseDtoList(List.of(bill1,bill2));
        List<Bill> bills = List.of(bill1,bill2);
        for(int i = 0; i < bills.size(); i++){
            assertEquals(billsResponseDto.get(i).getId(),bills.get(i).getId());
            assertEquals(billsResponseDto.get(i).getInstalments().get(0).getClass(), InstalmentResponseDto.class);
            compare(billsResponseDto.get(i),bills.get(i));
        }


    }


    void compare(BillDto billDto, Bill bill){
        assertEquals(billDto.getDescription(),bill.getDescription());
        assertEquals(billDto.getSupplier(),bill.getSupplier());
        assertEquals(billDto.getNoteNumber(),bill.getNoteNumber());
        assertEquals(billDto.getDescription(),bill.getDescription());
    }
}