package com.victorvilar.projetoempresa.configuration;

import com.victorvilar.projetoempresa.domain.*;
import com.victorvilar.projetoempresa.dto.adress.AddressResponseDto;
import com.victorvilar.projetoempresa.dto.contract.ContractResponseDto;
import com.victorvilar.projetoempresa.dto.contract.ItemContractResponseDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderResponseDto;
import com.victorvilar.projetoempresa.dto.supervisor.SupervisorResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(Supervisor.class, SupervisorResponseDto.class).
                addMappings(maper -> {
                    maper.map(src -> src.getCustomer().getCpfCnpj(),SupervisorResponseDto::setCustomerId);
                });

        mapper.typeMap(Contract.class, ContractResponseDto.class)
                .addMappings(maper ->{
                    maper.map(src -> src.getCustomer().getCpfCnpj(), ContractResponseDto::setCustomerId);
                });

        mapper.typeMap(Address.class, AddressResponseDto.class)
                .addMappings(maper ->{
                    maper.map(src -> src.getClient().getCpfCnpj(), AddressResponseDto::setCustomerId);
                });

        mapper.typeMap(ItemContract.class, ItemContractResponseDto.class)
                .addMappings(maper ->{
                    maper.map(src-> src.getResidue().getType(), ItemContractResponseDto::setResidue);
                    maper.map(src-> src.getEquipment().getEquipmentName(),ItemContractResponseDto::setEquipment);
                    maper.map(src-> src.getContract().getNumber(),ItemContractResponseDto::setContract);
                });

        mapper.typeMap(ServiceOrder.class, ServiceOrderResponseDto.class)
                .addMappings(maper ->{
                    maper.map(src-> src.getCustomer().getCpfCnpj(), ServiceOrderResponseDto::setCustomerId);

                });

        return mapper;
    }
}
