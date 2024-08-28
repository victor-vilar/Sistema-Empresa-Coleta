package com.victorvilar.projetoempresa.dto.serviceorder.interfaces;

import com.victorvilar.projetoempresa.domain.Vehicle;
import com.victorvilar.projetoempresa.dto.adress.AddressResponseDto;
import com.victorvilar.projetoempresa.dto.contract.interfaces.ItemContractResponseDto;

import java.io.Serializable;

public interface ServiceOrderResponseDto extends ServiceOrderDto, Serializable {

    ItemContractResponseDto getItemContract();
    String getCustomerId();
    Vehicle getVehicle();
    AddressResponseDto getAddress();
}
