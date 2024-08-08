package com.victorvilar.projetoempresa.dto.bill;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BillUpdateDto implements BillDto {

    @NotNull(message = "A bill to update must have an id")
    private Long id;
    @NotNull(message = "A bill to update must have a supplier")
    private String supplier;
    private String noteNumber;
    private String description;
    private List<InstalmentUpdateDto> instalments;

    public BillUpdateDto(){};

    public BillUpdateDto(Long id, String supplier, String noteNumber, String description, List<InstalmentUpdateDto> instalments) {
        this.id = id;
        this.supplier = supplier;
        this.noteNumber = noteNumber;
        this.description = description;
        this.instalments = instalments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getNoteNumber() {
        return noteNumber;
    }

    public void setNoteNumber(String noteNumber) {
        this.noteNumber = noteNumber;
    }

    public List<InstalmentUpdateDto> getInstalments() {
        return instalments;
    }

    public void setInstalments(List<InstalmentUpdateDto> instalments) {
        this.instalments = instalments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
