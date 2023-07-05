package com.clipboard.healthcare.service;

import com.clipboard.healthcare.common.Profession;
import com.clipboard.healthcare.exception.ActiveDocumentNotFoundException;
import com.clipboard.healthcare.exception.ActiveFacilityNotFoundException;
import com.clipboard.healthcare.exception.WorkerInactiveException;
import com.clipboard.healthcare.model.Document;
import com.clipboard.healthcare.model.Shift;
import com.clipboard.healthcare.model.Worker;
import com.clipboard.healthcare.repository.DocumentRepositoryImpl;
import com.clipboard.healthcare.repository.FacilityRepositoryImpl;
import com.clipboard.healthcare.repository.ShiftRepositoryImpl;
import com.clipboard.healthcare.repository.WorkerRepositoryImpl;
import com.clipboard.healthcare.response.ShiftResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class WorkerServiceImplTest {

    @Mock
    WorkerRepositoryImpl workerRepositoryImpl;

    @Mock
    DocumentRepositoryImpl documentRepositoryImpl;

    @Mock
    FacilityRepositoryImpl facilityRepositoryImpl;

    @Mock
    ShiftRepositoryImpl shiftRepositoryImpl;

    @InjectMocks
    WorkerServiceImpl workerServiceImpl;

    @Test
    void getAvailableShifts_WorkerInactiveException() {
        Mockito.when(workerRepositoryImpl.findById(anyInt())).thenReturn(new Worker(1, "name", false, Profession.CNA));
        Assertions.assertThrows(WorkerInactiveException.class, () -> workerServiceImpl.getAvailableShifts(1, "2023-02-01", "2023-02-02"));
    }

    @Test
    void getAvailableShifts_ActiveDocumentNotFoundException() {
        Mockito.when(workerRepositoryImpl.findById(anyInt())).thenReturn(new Worker(1, "name", true, Profession.CNA));
        Mockito.when(documentRepositoryImpl.findActiveDocumentsByWorkerId(anyInt())).thenReturn(Collections.emptyList());
        Assertions.assertThrows(ActiveDocumentNotFoundException.class, () -> workerServiceImpl.getAvailableShifts(1, "2023-02-01", "2023-02-02"));
    }

    @Test
    void getAvailableShifts_ActiveFacilityNotFoundException() {
        Mockito.when(workerRepositoryImpl.findById(anyInt())).thenReturn(new Worker(1, "name", true, Profession.CNA));
        Mockito.when(documentRepositoryImpl.findActiveDocumentsByWorkerId(anyInt())).thenReturn(Collections.singletonList(new Document(1, "name", true)));
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(2, Collections.singletonList(1));
        Mockito.when(facilityRepositoryImpl.findFacilityDocuments()).thenReturn(Collections.emptyMap());
        Assertions.assertThrows(ActiveFacilityNotFoundException.class, () -> workerServiceImpl.getAvailableShifts(1, "2023-02-01", "2023-02-02"));
    }

    @Test
    void getAvailableShifts() {
        Mockito.when(workerRepositoryImpl.findById(anyInt())).thenReturn(new Worker(1, "name", true, Profession.CNA));
        Mockito.when(documentRepositoryImpl.findActiveDocumentsByWorkerId(anyInt())).thenReturn(Collections.singletonList(new Document(1, "name", true)));
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(2, Collections.singletonList(1));
        Mockito.when(facilityRepositoryImpl.findFacilityDocuments()).thenReturn(map);
        Mockito.when(shiftRepositoryImpl.search(anyList(), any(Profession.class), anyString(), anyString())).thenReturn(Collections.singletonList(new ShiftResponse("2023-02-02", Collections.singletonList(new Shift(1, 2)))));

        List<ShiftResponse> shiftResponses = workerServiceImpl.getAvailableShifts(1, "2023-02-01", "2023-02-02");
        Assertions.assertNotNull(shiftResponses);
    }
}