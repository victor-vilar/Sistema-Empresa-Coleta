package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.domain.Bill;
import com.victorvilar.projetoempresa.domain.Instalment;
import com.victorvilar.projetoempresa.dto.bill.BillCreateDto;
import com.victorvilar.projetoempresa.dto.bill.BillResponseDto;
import com.victorvilar.projetoempresa.dto.bill.BillUpdateDto;
import com.victorvilar.projetoempresa.dto.bill.InstalmentUpdateDto;
import com.victorvilar.projetoempresa.exceptions.BillNotFoundException;
import com.victorvilar.projetoempresa.exceptions.ContractNotFoundException;
import com.victorvilar.projetoempresa.mappers.BillMapper;
import com.victorvilar.projetoempresa.mappers.InstalmentMapper;
import com.victorvilar.projetoempresa.repository.BillRepository;
import com.victorvilar.projetoempresa.repository.InstalmentRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillServiceTest {


    @InjectMocks
    private  BillService billService;
    @Mock
    private  BillRepository billRepository;
    @Mock
    private  InstalmentRepository instalmentRepository;
    @Mock
    private  BillMapper billMapper;
    @Mock
    private  InstalmentMapper instalmentMapper;



    Bill bill1;
    Bill bill2;
    List<Bill> bills;
    List<BillResponseDto> billResponseDtos;
    Instalment instalment1;
    Instalment instalment2;
    Bill bill1Updated;


    @BeforeEach
    void setUp() {
        this.init();
    }

    private void init(){
        this.initInstalment();
        this.bill1 = new Bill(1L,"ATACAO DE MOTORES","1000",List.of(this.instalment1,this.instalment2),"COMPRA DE DOIS MOTORES DE CAMINHÃO");
        this.bill2 = new Bill(2L,"Ligth","21232321",List.of(this.instalment1,this.instalment2),"conta de luz");
        this.bills = List.of(bill1, bill2);
        this.billResponseDtos = List.of(toResponseDto(bill1),toResponseDto(bill2));
    }

    private void initInstalment(){
        this.instalment1 = new Instalment();
        this.instalment2 = new Instalment();
        this.instalment1.setDueDate(LocalDate.of(2024,7,1));
        this.instalment2.setDueDate(LocalDate.of(2024,8,1));
        this.instalment1.setPaymentValue(BigDecimal.valueOf(500.00));
        this.instalment2.setPaymentValue(BigDecimal.valueOf(500.00));

    }

    private List<InstalmentUpdateDto> initInstalmentToUpdate(){
        InstalmentUpdateDto update1 = new InstalmentUpdateDto();
        InstalmentUpdateDto update2 = new InstalmentUpdateDto();
        update1.setDueDate(LocalDate.of(2025,7,1));
        update2.setDueDate(LocalDate.of(2025,8,1));
        update2.setPaymentValue(BigDecimal.valueOf(1500.00));
        update2.setPaymentValue(BigDecimal.valueOf(1500.00));
        return List.of(update1,update2);

    }

    private BillResponseDto toResponseDto(Bill bill){
        BillResponseDto dto = new BillResponseDto();
        dto.setId(bill.getId());
        dto.setDescription(bill.getDescription());
        dto.setSupplier(bill.getSupplier());
        dto.setNoteNumber(bill.getNoteNumber());
        return dto;
    }

    @Test
    @DisplayName("Successfull when try to get all bills")
    void getAll_WhenSuccessfull() {
        Mockito.when(billRepository.findAll()).thenReturn(this.bills);
        Mockito.when(billMapper.toBillResponseDtoList(this.bills)).thenReturn(this.billResponseDtos);
        List<BillResponseDto> returnedList = this.billService.getAll();
        assertEquals(2, returnedList.size());
        verify(billRepository,times(1)).findAll();
        assertEquals(this.billResponseDtos.get(0), returnedList.get(0));
        assertEquals(this.billResponseDtos.get(1), returnedList.get(1));
    }

    @Test
    @DisplayName("Successfull when try to get a bill by id")
    void getById_WhenSuccessfull() {
        Mockito.when(billRepository.findById(1L)).thenReturn(Optional.of(this.bill1));
        Mockito.when(billMapper.toBillResponseDto(this.bill1)).thenReturn(this.billResponseDtos.get(0));
        BillResponseDto responseDto = this.billService.getById(1L);
        assertNotNull(responseDto);
        verify(billRepository,times(1)).findById(1L);
        assertEquals(responseDto.getId(),bill1.getId());
        assertEquals(responseDto.getSupplier(),bill1.getSupplier());
    }

    @Test
    @DisplayName("Throws bill not found exception when bill id is not found")
    void getById_WhenPassInvalidId(){
        String billExceptionMessage = "A bill with this id was not found";
        Mockito.when(billRepository.findById(3L)).thenThrow(new BillNotFoundException(billExceptionMessage));
        BillNotFoundException exception = Assertions.assertThrows(BillNotFoundException.class,() -> billService.getById(3L));
        Assertions.assertEquals(exception.getClass(), BillNotFoundException.class);
        Assertions.assertEquals(billExceptionMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Successfull when save a new bill")
    void save_WhenSuccesfull() {
        Mockito.when(billMapper.toBill(any(BillCreateDto.class))).thenReturn(this.bill1);
        Mockito.when(billRepository.save(this.bill1)).thenReturn(this.bill1);
        Mockito.when(billMapper.toBillResponseDto(this.bill1)).thenReturn(this.billResponseDtos.get(0));
        BillResponseDto responseDto = billService.save(new BillCreateDto());
        verify(billMapper,times(1)).toBill(any(BillCreateDto.class));
        verify(billRepository,times(1)).save(bill1);
        verify(billMapper,times(1)).toBillResponseDto(bill1);
        assertEquals(this.bills.get(0).getId(),responseDto.getId());
        assertEquals(this.bills.get(0).getSupplier(), responseDto.getSupplier());

    }

    @Test
    @DisplayName("Successfull when try to delete a bill by id")
    void delete() {
        billService.delete(1L);
        verify(billRepository,times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Successfull when try to delete a list of bills")
    void deleteList(){
        billService.delete(List.of(1L,2L,3L));
        verify(billRepository,times(1)).deleteAllById(List.of(1L,2L,3L));
    }

//    @Test
//    @DisplayName("Successfull when try to update a bills")
//    void update() {
//        BillUpdateDto billUpdateDto = new BillUpdateDto();
//        billUpdateDto.setId(this.bill1.getId());
//        billUpdateDto.setDescription(this.bill1.getDescription());
//        billUpdateDto.setSupplier(this.bill1.getDescription());
//        billUpdateDto.setNoteNumber(this.bill1.getNoteNumber());
//        billUpdateDto.setInstalments(initInstalmentToUpdate());
//        Mockito.when(billRepository.findById(1L)).thenReturn(Optional.of(this.bill1));
//        Mockito.when(billRepository.save(this.bill1)).thenReturn(this.bill1);
//        Mockito.when(billMapper.toBillResponseDto(this.bill1)).thenReturn(this.billResponseDtos.get(0));
//        BillResponseDto responseDto = billService.update(billUpdateDto);
//        verify(billMapper,times(1)).toBill(billUpdateDto);
//        verify(billRepository,times(1)).save(bill1);
//        verify(billMapper,times(1)).toBillResponseDto(bill1);
//        assertEquals(this.bills.get(0).getId(),responseDto.getId());
//        assertEquals(this.bills.get(0).getSupplier(), responseDto.getSupplier());
//    }

    @Test
    @DisplayName("Successfull when try to delete a instalment or more from a bill")
    void deleteInstalment() {
        billService.deleteInstalment(1L);
        verify(instalmentRepository,times(1)).deleteById(1L);
    }
}