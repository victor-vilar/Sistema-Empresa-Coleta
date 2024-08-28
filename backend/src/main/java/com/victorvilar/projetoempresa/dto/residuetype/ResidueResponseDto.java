package com.victorvilar.projetoempresa.dto.residuetype;

import com.victorvilar.projetoempresa.dto.residuetype.interfaces.ResidueDto;

import java.io.Serializable;

public class ResidueResponseDto implements ResidueDto, Serializable {


    private Long id;
    private String type;
    private String description;


    public ResidueResponseDto() {
    }

    public ResidueResponseDto(String type){
        this.type = type;
    }

    public ResidueResponseDto(Long id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
