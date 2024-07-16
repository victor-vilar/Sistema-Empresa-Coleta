package com.victorvilar.projetoempresa.controllers;

import com.victorvilar.projetoempresa.controllers.interfaces.SystemController;
import com.victorvilar.projetoempresa.dto.bill.BillCreateDto;
import com.victorvilar.projetoempresa.dto.bill.BillResponseDto;
import com.victorvilar.projetoempresa.dto.bill.BillUpdateDto;
import com.victorvilar.projetoempresa.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bills")
public class BillController implements SystemController<BillCreateDto, BillUpdateDto, BillResponseDto> {

    private final BillService service;

    @Autowired
    public BillController(BillService billService){
        this.service = billService;
    }

    @Override
    public ResponseEntity<List<BillResponseDto>> getAll() {
        return ResponseEntity.ok().body(this.service.getAll());
    }

    @Override
    public ResponseEntity<BillResponseDto> getById(Long id) {
        return ResponseEntity.ok().body(this.service.getById(id));
    }

    @Override
    public ResponseEntity<BillResponseDto> save(BillCreateDto createDto) {
        return new ResponseEntity<BillResponseDto>(this.service.save(createDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<BillResponseDto> update(BillUpdateDto updateDto) {
        return ResponseEntity.ok().body(this.service.update(updateDto));
    }

    @DeleteMapping
    @RequestMapping("/delete/instalment/{id}")
    public ResponseEntity<?> deleteInstalment(@RequestParam Long id){
        this.service.deleteInstalment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * get the total of entitys persisted
     * @return integer of the count
     */
    @GetMapping("/count")
    public ResponseEntity<Integer> getEntityCount(){
        return ResponseEntity.ok(this.service.getEntityCount());
    }
}
