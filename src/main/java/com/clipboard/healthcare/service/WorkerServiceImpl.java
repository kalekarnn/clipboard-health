package com.clipboard.healthcare.service;

import com.clipboard.healthcare.exception.ActiveDocumentNotFoundException;
import com.clipboard.healthcare.exception.ActiveFacilityNotFoundException;
import com.clipboard.healthcare.exception.WorkerInactiveException;
import com.clipboard.healthcare.model.Document;
import com.clipboard.healthcare.model.Worker;
import com.clipboard.healthcare.repository.DocumentRepositoryImpl;
import com.clipboard.healthcare.repository.FacilityRepositoryImpl;
import com.clipboard.healthcare.repository.ShiftRepositoryImpl;
import com.clipboard.healthcare.repository.WorkerRepositoryImpl;
import com.clipboard.healthcare.response.ShiftResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl {

    @Autowired
    WorkerRepositoryImpl workerRepositoryImpl;

    @Autowired
    DocumentRepositoryImpl documentRepositoryImpl;

    @Autowired
    FacilityRepositoryImpl facilityRepositoryImpl;

    @Autowired
    ShiftRepositoryImpl shiftRepositoryImpl;

    public List<ShiftResponse> getAvailableShifts(int workerId, String start, String end) {

        Worker worker = workerRepositoryImpl.findById(workerId);
        if (!worker.isActive()) {
            throw new WorkerInactiveException("Worker is inactive: " + workerId);
        }

        List<Document> documents = documentRepositoryImpl.findActiveDocumentsByWorkerId(workerId);
        if (documents.isEmpty()) {
            throw new ActiveDocumentNotFoundException("Active documents are not found: " + workerId);
        }

        List<Integer> facilityIds = findActiveFacilitiesByDocuments(documents);
        if (facilityIds.isEmpty()) {
            throw new ActiveFacilityNotFoundException("Active facilities are not found: " + workerId);
        }

        //fetching the shifts assigned to worker
        List<ShiftResponse> alreadyAssignedShifts = shiftRepositoryImpl.getShiftsByWorkerId(workerId, start, end);

        List<ShiftResponse> totalAvailableShifts = shiftRepositoryImpl.search(facilityIds, worker.getProfession(), start, end);

        return
    }

    private List<Integer> findActiveFacilitiesByDocuments(List<Document> documents) {

        List<Integer> facilityIds = new ArrayList<>();
        Set<Integer> docIds = documents.stream().map(Document::getId).collect(Collectors.toSet());

        Map<Integer, List<Integer>> map = facilityRepositoryImpl.findFacilityDocuments();
        map.forEach((k, v) -> {
            if (docIds.containsAll(v)) {
                facilityIds.add(k);
            }
        });
        return facilityIds;
    }

}
