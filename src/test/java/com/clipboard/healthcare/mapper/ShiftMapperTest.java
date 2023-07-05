package com.clipboard.healthcare.mapper;

import com.clipboard.healthcare.model.Shift;
import com.clipboard.healthcare.response.ShiftResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ShiftMapperTest {
    @Mock
    ResultSet resultSet;

    @InjectMocks
    ShiftMapper shiftMapper;

    @Test
    void mapRow() throws SQLException {
        List<Shift> shifts = new ArrayList<>();
        shifts.add(new Shift( 751720,1));
        shifts.add(new Shift( 751721,2));
        shifts.add(new Shift( 751722,3));

        ShiftResponse shiftResponse = new ShiftResponse("2023-02-02", shifts);

        Mockito.when(resultSet.getString("start_date")).thenReturn("2023-02-02");
        Mockito.when(resultSet.getString("id_fid")).thenReturn("751720&1@751721&2@751722&3");

        ShiftResponse result = shiftMapper.mapRow(resultSet, 0);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(shiftResponse, result);
    }
}