package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.domain.bill.Bill;
import com.victorvilar.projetoempresa.domain.bill.Instalment;
import com.victorvilar.projetoempresa.dto.bill.BillCreateDto;
import com.victorvilar.projetoempresa.dto.bill.BillResponseDto;
import com.victorvilar.projetoempresa.dto.bill.BillUpdateDto;
import com.victorvilar.projetoempresa.dto.bill.InstalmentUpdateDto;
import com.victorvilar.projetoempresa.exceptions.BillNotFoundException;
import com.victorvilar.projetoempresa.mappers.BillMapper;
import com.victorvilar.projetoempresa.repository.BillRepository;
import com.victorvilar.projetoempresa.services.interfaces.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService implements SystemService<BillCreateDto, BillUpdateDto, BillResponseDto> {

    private final BillMapper billMapper;
    private final BillRepository billRepository;

    @Autowired
    public BillService(BillMapper billMapper, BillRepository billRepository){
        this.billMapper = billMapper;
        this.billRepository = billRepository;
    }

    @Override
    public List<BillResponseDto> getAll() {
        return this.billMapper.toBillResponseDtoList(this.billRepository.findAll());
    }

    @Override
    public BillResponseDto getById(Long id) {
        return this.billMapper.toBillResponseDto(this.billRepository.findById(id).orElseThrow(() -> new BillNotFoundException("A bill with this id was not found")));
    }

    @Override
    public BillResponseDto save(BillCreateDto createDto) {
        Bill bill = this.billMapper.toBill(createDto);
        return this.billMapper.toBillResponseDto(this.billRepository.save(bill));
    }

    @Override
    public void delete(List<Long> ids) {
        this.billRepository.deleteAllById(ids);
    }

    public void delete(Long id){
        this.billRepository.deleteById(id);
    }

    @Override
    public BillResponseDto update(BillUpdateDto updateDto) {
        Bill bill = this.billRepository.findById(updateDto.getId()).orElseThrow(() -> new BillNotFoundException("A bill with this id was not found"))
        updateDto.getInstalments().stream().forEach(instalment ->{
            if(instalment.getId() = null){
                bill.addNewStalment(instalment);
            }else{
                this.updateInstalmentsOfBill(instalment);
            }
        });

    }

    private Instalment  updateInstalmentsOfBill(InstalmentUpdateDto instalmentUpdateDto){

    }


}
