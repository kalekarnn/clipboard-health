package com.clipboard.healthcare.controller;

import com.clipboard.healthcare.service.WorkerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class WorkerControllerTest {

    @Mock
    private WorkerServiceImpl workerServiceImpl;

    @InjectMocks
    private WorkerController workerController;

    @Test
    void searchShifts() {

        workerController.searchShifts(1, "2023-01-01", "2023-01-30");
        Mockito.verify(workerServiceImpl, Mockito.times(1)).getAvailableShifts(anyInt(), anyString(), anyString());
    }
}