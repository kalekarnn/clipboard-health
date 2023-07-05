package com.clipboard.healthcare.repository;

import com.clipboard.healthcare.common.query.DocumentQueries;
import com.clipboard.healthcare.exception.DocumentOperationException;
import com.clipboard.healthcare.mapper.DocumentMapper;
import com.clipboard.healthcare.model.Document;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class DocumentRepositoryImpl {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Document> findActiveDocumentsByWorkerId(int workerId) {

        try {
            return jdbcTemplate.query(DocumentQueries.FIND_ACTIVE_DOCUMENTS_BY_WORKER_ID, new DocumentMapper(), workerId);
        } catch (Exception exception) {
            log.error(exception.getStackTrace());
            throw new DocumentOperationException(exception.getMessage());
        }
    }
}
