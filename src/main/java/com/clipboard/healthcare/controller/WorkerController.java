package com.clipboard.healthcare.controller;

import com.clipboard.healthcare.response.ShiftResponse;
import com.clipboard.healthcare.service.WorkerServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
public class WorkerController {

    @Autowired
    WorkerServiceImpl workerServiceImpl;

    @GetMapping("/workers/{workerId}/shifts")
    public ResponseEntity<List<ShiftResponse>> searchShifts(@PathVariable int workerId,
                                                            @RequestParam("start") String start,
                                                            @RequestParam("end") String end
    ) {
        final List<ShiftResponse> shifts = workerServiceImpl.getAvailableShifts(workerId, start, end);
        return new ResponseEntity<>(shifts, HttpStatus.OK);
    }

}
