package com.clipboard.healthcare.repository;

import com.clipboard.healthcare.common.Profession;
import com.clipboard.healthcare.common.query.ShiftQueries;
import com.clipboard.healthcare.exception.ShiftOperationException;
import com.clipboard.healthcare.mapper.ShiftMapper;
import com.clipboard.healthcare.response.ShiftResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Log4j2
public class ShiftRepositoryImpl {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ShiftResponse> search(List<Integer> facilityIds, Profession profession, String start, String end) {

        try {
            String facilities = facilityIds.stream().map(id -> "'" + id + "'").collect(Collectors.joining(","));
            return jdbcTemplate.query(String.format(ShiftQueries.SEARCH_SHIFTS_BY_START_AND_END_DATE, profession, facilities), new ShiftMapper(),
                    start,
                    end);
        } catch (Exception exception) {
            log.error(exception.getStackTrace());
            throw new ShiftOperationException(exception.getMessage());
        }
    }

    public List<ShiftResponse> getShiftsByWorkerId(int workerId, String start, String end) {
        try {

            return jdbcTemplate.query(String.format(ShiftQueries.GET_SHIFTS_BY_WORKER_ID_START_AND_END_DATE, workerId), new ShiftMapper(),
                    start,
                    end);
        } catch (Exception exception) {
            log.error(exception.getStackTrace());
            throw new ShiftOperationException(exception.getMessage());
        }
    }
}
