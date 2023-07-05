package com.clipboard.healthcare.mapper;

import com.clipboard.healthcare.model.FacilityDocument;
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
class FacilityDocumentMapperTest {
    @Mock
    ResultSet resultSet;

    @InjectMocks
    FacilityDocumentMapper facilityDocumentMapper;

    @Test
    void mapRow() throws SQLException {
        FacilityDocument facilityDocument = new FacilityDocument(1, 2);
        Mockito.when(resultSet.getInt("facility_id")).thenReturn(1);
        Mockito.when(resultSet.getInt("document_id")).thenReturn(2);
        FacilityDocument result = facilityDocumentMapper.mapRow(resultSet, 0);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(facilityDocument, result);
    }
}