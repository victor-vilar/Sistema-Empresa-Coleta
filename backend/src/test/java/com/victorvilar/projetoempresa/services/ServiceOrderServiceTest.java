package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.mappers.ContractMapper;
import com.victorvilar.projetoempresa.mappers.ServiceOrderMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Contract service tests class")
class ServiceOrderServiceTest {

    @InjectMocks
    private ServiceOrderService serviceOrderService;

    @Mock
    private ServiceOrderMapper serviceOrderMapper;
}