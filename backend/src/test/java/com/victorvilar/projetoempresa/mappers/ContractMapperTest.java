package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.Contract;
import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.domain.ItemContract;
import com.victorvilar.projetoempresa.domain.CollectionFrequency;
import com.victorvilar.projetoempresa.dto.contract.*;
import com.victorvilar.projetoempresa.dto.contract.interfaces.*;
import com.victorvilar.projetoempresa.enums.MeasurementUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContractMapperTest {

    @Autowired
    private ContractMapper mapper;

    Customer customer1;
    Contract contract1;
    Contract contract2;
    ContractCreateDto contractCreateDto;
    ContractUpdateDto contractUpdateDto;
    ItemContract itemContract1;
    ItemContract itemContract2;


    @BeforeEach
    void setUp(){
        customer1 = new Customer("82508641073","EMPRESA1",true);

        itemContract1 = new ItemContract();
        itemContract1.setMeasurementUnit(MeasurementUnit.LITROS);
        itemContract2 = new ItemContract();
        itemContract2.setMeasurementUnit(MeasurementUnit.LITROS);

        contract1 = Contract.builder()
                .number("111")
                .beginDate(LocalDate.now())
                .endDate(LocalDate.now())
                .customer(customer1).
                build();

        contract1.setId(1L);

        contract2 = Contract.builder()
                .number("222")
                .beginDate(LocalDate.now())
                .endDate(LocalDate.now())
                .customer(customer1)
                .build();

        contract2.setId(2L);

        contractCreateDto = new ContractCreateDto("111",LocalDate.now(),LocalDate.now(), List.of(new ItemContractCreateDto(),new ItemContractCreateDto()),"82508641073");
        contractUpdateDto = new ContractUpdateDto(1L,"111",LocalDate.now(),LocalDate.now(), List.of(new ItemContractUpdateDto(),new ItemContractUpdateDto()),"82508641073","ATIVO");
        contractCreateDto.setContractStatus("ATIVO");
    }

    @Test
    void toContract_WhenPassingContractCreateDto() {
        Contract contract = this.mapper.toContract(contractCreateDto);
        contract.getItens().forEach(e -> assertEquals(e.getClass(),ItemContract.class ));
        compareRequestDto(contractCreateDto,contract);
    }

    @Test
    void toContract_WhenPassingContractUpdateDto() {
        Contract contract = this.mapper.toContract(contractUpdateDto);
        contract.getItens().forEach(e -> assertEquals(e.getClass(),ItemContract.class ));
        compareRequestDto(contractUpdateDto,contract);
    }

    @Test
    void toContractResponseDto() {
        ContractResponseImplDto dto = this.mapper.toContractResponseDto(contract1);
        dto.getItens().forEach(e-> assertEquals(e.getClass(), ItemContractResponseImplDto.class ));
        compareResponseDto(dto,contract1);
    }

    @Test
    void toContractResponseDtoList() {
        List<ContractResponseImplDto> dtos = this.mapper.toContractResponseDtoList(List.of(contract1,contract2));
        List<Contract> contracts = List.of(contract1,contract2);
        assertEquals(dtos.size(),contracts.size());
        dtos.forEach(dto -> dto.getItens().forEach(e-> assertEquals(e.getClass(), ItemContractResponseImplDto.class)));
        for(int i =0; i < dtos.size();i++){
            compareResponseDto(dtos.get(i),contracts.get(i));
        }
    }

    @Test
    void testItemContract(){}

    void compareRequestDto(ContractRequestDto dto , Contract contract){
        assertEquals(dto.getContractStatus(), contract.getContractStatus());
        compare(dto,contract);
    }

    void compareResponseDto(ContractResponseDto dto, Contract contract){
        assertEquals(dto.getContractStatus(), contract.getContractStatus().toString());
        compare(dto,contract);
    }

    void compare(ContractDto dto, Contract contract){
        assertEquals(dto.getNumber(),contract.getNumber());
        assertEquals(dto.getBeginDate(),contract.getBeginDate());
        assertEquals(dto.getEndDate(),contract.getEndDate());
    }


}