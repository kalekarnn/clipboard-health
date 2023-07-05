package com.clipboard.healthcare.repository;

import com.clipboard.healthcare.exception.FacilityOperationException;
import com.clipboard.healthcare.mapper.FacilityDocumentMapper;
import com.clipboard.healthcare.model.FacilityDocument;
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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class FacilityRepositoryImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    FacilityRepositoryImpl facilityRepositoryImpl;

    @Test
    void findFacilityDocuments() {
        FacilityDocument facilityDocument1 = new FacilityDocument(1, 2);
        FacilityDocument facilityDocument2 = new FacilityDocument(1, 3);
        FacilityDocument facilityDocument3 = new FacilityDocument(2, 5);

        List<FacilityDocument> facilityDocuments = new ArrayList<>();
        facilityDocuments.add(facilityDocument1);
        facilityDocuments.add(facilityDocument2);
        facilityDocuments.add(facilityDocument3);

        Mockito.when(jdbcTemplate.query(anyString(), any(FacilityDocumentMapper.class))).thenReturn(facilityDocuments);
        Map<Integer, List<Integer>> map = facilityRepositoryImpl.findFacilityDocuments();
        Assertions.assertNotNull(map);
        Assertions.assertEquals(2, map.size());
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);

        Assertions.assertEquals(list, map.get(1));
        Assertions.assertEquals(Collections.singletonList(5), map.get(2));
    }

    @Test
    void findFacilityDocuments_exception() {
        Mockito.when(jdbcTemplate.query(anyString(), any(FacilityDocumentMapper.class))).thenThrow(RuntimeException.class);
        assertThrows(FacilityOperationException.class, () -> facilityRepositoryImpl.findFacilityDocuments());
    }
}