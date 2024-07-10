package com.victorvilar.projetoempresa.controllers;

import com.victorvilar.projetoempresa.controllers.interfaces.EntityOfCustomerController;
import com.victorvilar.projetoempresa.controllers.interfaces.SystemController;
import com.victorvilar.projetoempresa.dto.bill.BillCreateDto;
import com.victorvilar.projetoempresa.dto.bill.BillResponseDto;
import com.victorvilar.projetoempresa.dto.bill.BillUpdateDto;
import com.victorvilar.projetoempresa.services.BillService;
import com.victorvilar.projetoempresa.services.interfaces.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController implements SystemController<BillCreateDto, BillUpdateDto, BillResponseDto> {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService){
        this.billService = billService;
    }

    @Override
    public ResponseEntity<List<BillResponseDto>> getAll() {
        return ResponseEntity.ok().body(this.billService.getAll());
    }

    @Override
    public ResponseEntity<BillResponseDto> getById(Long id) {
        return ResponseEntity.ok().body(this.billService.getById(id));
    }

    @Override
    public ResponseEntity<BillResponseDto> save(BillCreateDto createDto) {
        return new ResponseEntity<BillResponseDto>(this.billService.save(createDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        this.billService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<BillResponseDto> update(BillUpdateDto updateDto) {
        return ResponseEntity.ok().body(this.billService.update(updateDto));
    }
}
