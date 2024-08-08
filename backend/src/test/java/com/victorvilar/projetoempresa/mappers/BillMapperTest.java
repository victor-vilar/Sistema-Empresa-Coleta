package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.Bill;
import com.victorvilar.projetoempresa.dto.bill.BillCreateDto;
import com.victorvilar.projetoempresa.dto.bill.BillUpdateDto;
import com.victorvilar.projetoempresa.dto.bill.BillDto;
import com.victorvilar.projetoempresa.dto.bill.BillResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @BeforeEach
    void setUp(){

        bill1 = new Bill(1L,"Supplier1","111", List.of(),"conta1");
        bill2 = new Bill(2L,"Supplier2","222", List.of(),"conta2");
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
        compare(billResponseDto,bill1);
    }

    @Test
    void toBillResponseDtoList() {
        List<BillResponseDto> billsResponseDto = this.mapper.toBillResponseDtoList(List.of(bill1,bill2));
        List<Bill> bills = List.of(bill1,bill2);
        for(int i = 0; i < bills.size(); i++){
            assertEquals(billsResponseDto.get(i).getId(),bills.get(i).getId());
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