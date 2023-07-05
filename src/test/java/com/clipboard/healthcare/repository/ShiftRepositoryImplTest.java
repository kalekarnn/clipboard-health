package com.clipboard.healthcare.repository;

import com.clipboard.healthcare.common.Profession;
import com.clipboard.healthcare.exception.ShiftOperationException;
import com.clipboard.healthcare.mapper.ShiftMapper;
import com.clipboard.healthcare.model.Shift;
import com.clipboard.healthcare.response.ShiftResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class ShiftRepositoryImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    ShiftRepositoryImpl shiftRepositoryImpl;

    @Test
    void search() {
        List<Shift> shifts = new ArrayList<>();
        shifts.add(new Shift(751720, 1));

        ShiftResponse shiftResponse = new ShiftResponse("2023-02-02", shifts);

        Mockito.when(jdbcTemplate.query(anyString(), any(ShiftMapper.class), any(), any())).thenReturn(Collections.singletonList(shiftResponse));
        List<ShiftResponse> result = shiftRepositoryImpl.search(Collections.singletonList(1), Profession.CNA, "2023-01-01", "2023-02-15");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(shiftResponse, result.get(0));
    }

    @Test
    void search_exception() {
        Mockito.when(jdbcTemplate.query(anyString(), any(ShiftMapper.class), any(), any(), any(), any())).thenThrow(RuntimeException.class);
        assertThrows(ShiftOperationException.class, () -> shiftRepositoryImpl.search(Collections.singletonList(1), Profession.CNA, "2023-01-01", "2023-01-30"));
    }
}