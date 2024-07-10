package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.bill.Bill;
import com.victorvilar.projetoempresa.dto.bill.BillCreateDto;
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

    public Bill toBill(BillCreateDto billCreateDto){
        return this.mapper.map(billCreateDto,Bill.class);
    }

    public Bill toBill(BillUpdateDto billUpadteDto){
        return this.mapper.map(billUpadteDto,Bill.class);
    }

    public List<Bill> toBillList(List<BillUpdateDto> billUpdateDtoList){
        return billUpdateDtoList.stream().map(this::toBill).toList();
    }

    public BillResponseDto toBillResponseDto(Bill bill){
        return this.mapper.map(bill,BillResponseDto.class);
    }

    public List<BillResponseDto> toBillResponseDtoList(List<Bill> bills){
        return bills.stream().map(this::toBillResponseDto).toList();
    }



}
