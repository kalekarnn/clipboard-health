package com.clipboard.healthcare.model;

import com.clipboard.healthcare.common.Profession;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;


@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include. NON_NULL)
public class Shift {

    private Integer id;
    private OffsetDateTime start;
    private OffsetDateTime end;
    private Profession profession;
    private Boolean isDeleted;
    private Integer facilityId;
    private Integer workerId;

    public Shift(int id, int facilityId) {
        this.id = id;
        this.facilityId = facilityId;
    }
}
