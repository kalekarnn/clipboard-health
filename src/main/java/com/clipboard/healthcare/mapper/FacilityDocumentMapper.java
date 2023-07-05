package com.clipboard.healthcare.mapper;

import com.clipboard.healthcare.model.FacilityDocument;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacilityDocumentMapper implements RowMapper<FacilityDocument> {


    @Override
    public FacilityDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        int facilityId = rs.getInt("facility_id");
        int documentId = rs.getInt("document_id");

        return new FacilityDocument(facilityId, documentId);
    }
}