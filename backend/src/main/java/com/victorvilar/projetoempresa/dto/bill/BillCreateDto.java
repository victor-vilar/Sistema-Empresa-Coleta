package com.victorvilar.projetoempresa.dto.bill;

import com.victorvilar.projetoempresa.dto.bill.interfaces.BillDto;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BillCreateDto implements BillDto {

    @NotNull(message = "A bill to update must have a supplier")
    private String supplier;
    private String noteNumber;
    private String description;
    private List<InstalmentCreateDto> instalments = new ArrayList<>();

    public BillCreateDto(){};

    public BillCreateDto(String supplier, String noteNumber, String description, List<InstalmentCreateDto> instalments) {
        this.supplier = supplier;
        this.noteNumber = noteNumber;
        this.description = description;
        this.instalments = instalments;
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
