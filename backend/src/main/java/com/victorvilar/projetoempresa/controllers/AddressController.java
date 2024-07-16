package com.victorvilar.projetoempresa.controllers;

import com.victorvilar.projetoempresa.dto.adress.AddressCreateDto;
import com.victorvilar.projetoempresa.dto.adress.AddressResponseDto;
import com.victorvilar.projetoempresa.dto.adress.AddressUpdateDto;
import com.victorvilar.projetoempresa.services.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Address controller
 * @author Victor Vilar
 * @since 05/01/2023
 */

@RestController
@RequestMapping("/v1/addresses")
public class AddressController {

    private final AddressService service;


    @Autowired
    public AddressController(AddressService service){
        this.service = service;
    }

    /**
     * get all address
     * @return list of address
     */
    @GetMapping()
    public ResponseEntity<List<AddressResponseDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getAll());
    }

    /**
     * get all address of a client
     * @param clientId id of a client
     * @return a list of address of a client
     */
    @GetMapping("by-customer/{clientId}")
    public ResponseEntity<List<AddressResponseDto>> getAllByCustomerId(@PathVariable String clientId){
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getAllByCustomerId(clientId));
    }

    /**
     * get an address by id
     * @param id
     * @return address
     */
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getById(id));
    }

    /**
     *  create a new addresss
     * @param addressCreateDto address body to save
     * @return saved address
     */
    @PostMapping()
    public ResponseEntity<AddressResponseDto> save(@Valid @RequestBody AddressCreateDto addressCreateDto){
        return ResponseEntity.status(HttpStatus.OK).body(this.service.save(addressCreateDto));
    }

    /**
     * delete an address
     * @param id of an address
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * update an address
     * @return
     */
    @PutMapping()
    public ResponseEntity<AddressResponseDto> update(@RequestBody AddressUpdateDto addressUpdateDto){
        return ResponseEntity.status(HttpStatus.OK).body(this.service.update(addressUpdateDto));
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
