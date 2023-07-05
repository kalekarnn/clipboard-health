package com.clipboard.healthcare.repository;

import com.clipboard.healthcare.common.query.WorkerQueries;
import com.clipboard.healthcare.exception.WorkerNotFoundException;
import com.clipboard.healthcare.exception.WorkerOperationException;
import com.clipboard.healthcare.mapper.WorkerMapper;
import com.clipboard.healthcare.model.Worker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class WorkerRepositoryImpl {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Worker findById(int workerId) {

        try {
            List<Worker> workers = jdbcTemplate.query(WorkerQueries.FIND_WORKER_BY_ID, new WorkerMapper(), workerId);
            if (!workers.isEmpty()) {
                return workers.get(0);
            }
            throw new WorkerNotFoundException("Worker not found: " + workerId);
        } catch (WorkerNotFoundException exception) {
            throw exception;
        } catch (Exception exception) {
            log.error(exception.getStackTrace());
            throw new WorkerOperationException(exception.getMessage());
        }
    }
}
