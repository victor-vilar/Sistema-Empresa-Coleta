package com.victorvilar.projetoempresa.controllers;

import com.victorvilar.projetoempresa.dto.bill.BillCreateDto;
import com.victorvilar.projetoempresa.dto.bill.BillResponseDto;
import com.victorvilar.projetoempresa.dto.bill.BillUpdateDto;
import com.victorvilar.projetoempresa.services.interfaces.SystemService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillController implements SystemService<BillCreateDto, BillUpdateDto, BillResponseDto> {


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

    @Override
    public BillResponseDto update(BillUpdateDto updateDto) {
        return null;
    }
}
