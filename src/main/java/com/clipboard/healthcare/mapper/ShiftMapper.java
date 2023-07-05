package com.clipboard.healthcare.mapper;

import com.clipboard.healthcare.model.Shift;
import com.clipboard.healthcare.response.ShiftResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShiftMapper implements RowMapper<ShiftResponse> {

    @Override
    public ShiftResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

        String startDate = rs.getString("start_date");
        String IdFidStr = rs.getString("id_fid");

        List<Shift> shifts = Arrays.stream(IdFidStr.split("@"))
                .map(s -> new Shift(Integer.parseInt(s.split("&")[0]), Integer.parseInt(s.split("&")[1]))).collect(Collectors.toList());

        return new ShiftResponse(startDate, shifts);
    }
}