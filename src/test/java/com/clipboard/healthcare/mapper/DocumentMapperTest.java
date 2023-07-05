package com.clipboard.healthcare.mapper;

import com.clipboard.healthcare.model.Document;
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
class DocumentMapperTest {

    @Mock
    ResultSet resultSet;

    @InjectMocks
    DocumentMapper documentMapper;

    @Test
    void mapRow() throws SQLException {
        Document document = new Document(1, "name", true);
        Mockito.when(resultSet.getInt("id")).thenReturn(1);
        Mockito.when(resultSet.getString("name")).thenReturn("name");
        Mockito.when(resultSet.getBoolean("is_active")).thenReturn(true);
        Document result = documentMapper.mapRow(resultSet, 0);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(document, result);
    }
}