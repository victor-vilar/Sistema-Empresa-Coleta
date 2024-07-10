package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.dto.bill.BillCreateDto;
import com.victorvilar.projetoempresa.dto.bill.BillResponseDto;
import com.victorvilar.projetoempresa.dto.bill.BillUpdateDto;
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
        return null;
    }

    @Override
    public BillResponseDto getById(Long id) {
        return null;
    }

    @Override
    public BillResponseDto save(BillCreateDto createDto) {
        return null;
    }

    @Override
    public void delete(List<Long> ids) {

    }

    public void delete(Long id){

    }

    @Override
    public BillResponseDto update(BillUpdateDto updateDto) {
        return null;
    }
}
