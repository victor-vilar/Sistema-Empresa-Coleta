package com.victorvilar.projetoempresa.dto.bill;

import com.victorvilar.projetoempresa.domain.Bill;

import java.util.List;

public class BillResponseDto implements BillDto {

    private Long id;
    private String supplier;
    private String noteNumber;
    private String description;
    private List<InstalmentResponseDto> instalments;

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

    public List<InstalmentResponseDto> getInstalments() {
        return instalments;
    }

    public void setInstalments(List<InstalmentResponseDto> stalments) {
        this.instalments = stalments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
