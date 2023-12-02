package com.victorvilar.projetoempresa.dto.residuetype;

public class ResidueResponseDto {


    private Long id;
    private String type;
    private String description;


    public ResidueResponseDto() {
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
