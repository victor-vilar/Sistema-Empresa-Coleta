package com.victorvilar.projetoempresa.dto.serviceorder.interfaces;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ServiceOrderDto {

    LocalDate getEmissionDate();
    LocalDate getServiceExpectedDate();
    LocalDate getServiceExecutedDate();
    String getObservation();
    LocalTime getServiceTime();
    Long getAmountCollected();
    String getIneaManifest();
    String getOsFileUrl();



}
