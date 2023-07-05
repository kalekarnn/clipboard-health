package com.clipboard.healthcare.repository;

import com.clipboard.healthcare.common.query.FacilityQueries;
import com.clipboard.healthcare.exception.FacilityOperationException;
import com.clipboard.healthcare.mapper.FacilityDocumentMapper;
import com.clipboard.healthcare.model.FacilityDocument;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Log4j2
public class FacilityRepositoryImpl {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Map<Integer, List<Integer>> findFacilityDocuments() {

        try {
            Map<Integer, List<Integer>> map = new HashMap<>();
            List<FacilityDocument> facilityDocuments = jdbcTemplate.query(FacilityQueries.FIND_FACILITY_DOCUMENTS, new FacilityDocumentMapper());
            facilityDocuments.forEach(facilityDocument -> {
                List<Integer> docs = map.get(facilityDocument.getFacilityId());
                if (docs == null) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(facilityDocument.getDocumentId());

                    map.put(facilityDocument.getFacilityId(), list);
                } else {
                    docs.add(facilityDocument.getDocumentId());
                    map.put(facilityDocument.getFacilityId(), docs);
                }
            });
            return map;
        } catch (Exception exception) {
            log.error(exception.getStackTrace());
            throw new FacilityOperationException(exception.getMessage());
        }
    }
}

