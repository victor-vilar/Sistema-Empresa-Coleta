package com.victorvilar.projetoempresa.dto.bill;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BillCreateDto implements BillDto {

    @NotNull(message = "A bill to update must have a supplier")
    private String supplier;
    private String noteNumber;
    private String description;
    private List<InstalmentCreateDto> instalments;

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

    public List<InstalmentCreateDto> getInstalments() {
        return instalments;
    }

    public void setInstalments(List<InstalmentCreateDto> instalments) {
        this.instalments = instalments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
