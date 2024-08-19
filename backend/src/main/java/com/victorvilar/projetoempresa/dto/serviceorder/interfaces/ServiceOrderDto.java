package com.victorvilar.projetoempresa.dto.serviceorder.interfaces;

import com.victorvilar.projetoempresa.domain.Vehicle;
import com.victorvilar.projetoempresa.dto.contract.interfaces.ItemContractResponseDto;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ServiceOrderDto {

    LocalDate getEmissionDate();
    LocalDate getServiceExpectedDate();
    LocalDate serviceExecutedDate();
    String getObservation();
    LocalTime getServiceTime();
    Long getAmountCollected();
    String getIneaManifest();
    String getOsFileUrl();



}
