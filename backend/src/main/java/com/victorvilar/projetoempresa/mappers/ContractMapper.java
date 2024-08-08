package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.dto.contract.ContractCreateDto;
import com.victorvilar.projetoempresa.dto.contract.ContractResponseImplDto;
import com.victorvilar.projetoempresa.dto.contract.ContractUpdateDto;
import com.victorvilar.projetoempresa.domain.Contract;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContractMapper {


    private final ModelMapper mapper;

    @Autowired
    public ContractMapper(ModelMapper mapper){
        this.mapper = mapper;
    }


    public Contract toContract(ContractCreateDto contractCreateDto){
        Contract contract = this.mapper.map(contractCreateDto, Contract.class);
        contract.setId(null);
        return contract;
    }

    public Contract toContract(ContractUpdateDto contractUpdateDto){
        return this.mapper.map(contractUpdateDto,Contract.class);
    }

    public ContractResponseImplDto toContractResponseDto(Contract contract){
        return this.mapper.map(contract, ContractResponseImplDto.class);
    }

    public List<ContractResponseImplDto> toContractResponseDtoList(List<Contract> contracts){
        return contracts.stream().map(c ->this.toContractResponseDto(c)).collect(Collectors.toList());

    }

}
