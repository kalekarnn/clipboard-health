package com.clipboard.healthcare.mapper;

import com.clipboard.healthcare.common.Profession;
import com.clipboard.healthcare.model.Document;
import com.clipboard.healthcare.model.Worker;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentMapper implements RowMapper<Document> {


    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        boolean active = rs.getBoolean("is_active");
        return new Document(id, name, active);
    }
}