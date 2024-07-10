package com.victorvilar.projetoempresa.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BillMapper {

    private final ModelMapper mapper;

    @Autowired
    public BillMapper( ModelMapper mapper){
        this.mapper = mapper;
    }

}
