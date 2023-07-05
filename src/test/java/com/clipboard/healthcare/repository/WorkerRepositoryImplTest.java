package com.clipboard.healthcare.repository;

import com.clipboard.healthcare.common.Profession;
import com.clipboard.healthcare.exception.WorkerNotFoundException;
import com.clipboard.healthcare.exception.WorkerOperationException;
import com.clipboard.healthcare.mapper.WorkerMapper;
import com.clipboard.healthcare.model.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class WorkerRepositoryImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    private WorkerRepositoryImpl workerRepositoryImpl;

    @Test
    void findById() {
        Worker test = new Worker(1, "test", true, Profession.CNA);
        Mockito.when(jdbcTemplate.query(anyString(), any(WorkerMapper.class), anyInt())).thenReturn(Collections.singletonList(test));
        Worker worker = workerRepositoryImpl.findById(1);
        Assertions.assertNotNull(worker);
        Assertions.assertEquals(test, worker);
    }

    @Test
    void findById_not_found() {
        Mockito.when(jdbcTemplate.query(anyString(), any(WorkerMapper.class), anyInt())).thenReturn(Collections.emptyList());
        assertThrows(WorkerNotFoundException.class, () -> workerRepositoryImpl.findById(1));
    }

    @Test
    void findById_exception() {
        Mockito.when(jdbcTemplate.query(anyString(), any(WorkerMapper.class), anyInt())).thenThrow(RuntimeException.class);
        assertThrows(WorkerOperationException.class, () -> workerRepositoryImpl.findById(1));
    }
}