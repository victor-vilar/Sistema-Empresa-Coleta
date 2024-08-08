package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.Bill;
import com.victorvilar.projetoempresa.domain.Instalment;
import com.victorvilar.projetoempresa.dto.bill.BillCreateDto;
import com.victorvilar.projetoempresa.dto.bill.BillDto;
import com.victorvilar.projetoempresa.dto.bill.BillResponseDto;
import com.victorvilar.projetoempresa.dto.bill.BillUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BillMapper {

    private final ModelMapper mapper;

    @Autowired
    public BillMapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    public Bill toBill(BillDto billDto){
        Bill bill = this.mapper.map(billDto,Bill.class);
        return bill;
    }

    public List<Bill> toBillList(List<? extends BillDto> billDtos){
        return billDtos.stream().map(this::toBill).toList();
    }

    public BillResponseDto toBillResponseDto(Bill bill){
        return this.mapper.map(bill,BillResponseDto.class);
    }

    public List<BillResponseDto> toBillResponseDtoList(List<Bill> bills){
        return bills.stream().map(this::toBillResponseDto).toList();
    }



}
