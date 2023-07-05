package com.clipboard.healthcare.mapper;

import com.clipboard.healthcare.common.Profession;
import com.clipboard.healthcare.model.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;


@ExtendWith(MockitoExtension.class)
class WorkerMapperTest {
    @Mock
    ResultSet resultSet;

    @InjectMocks
    WorkerMapper workerMapper;

    @Test
    void mapRow() throws SQLException {
        Worker worker = new Worker(1, "name", true, Profession.CNA);
        Mockito.when(resultSet.getInt("id")).thenReturn(1);
        Mockito.when(resultSet.getString("name")).thenReturn("name");
        Mockito.when(resultSet.getBoolean("is_active")).thenReturn(true);
        Mockito.when(resultSet.getString("profession")).thenReturn(Profession.CNA.name());
        Worker result = workerMapper.mapRow(resultSet, 0);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(worker, result);
    }
}