package com.clipboard.healthcare.response;

import com.clipboard.healthcare.model.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ShiftResponse {
    private String startDate;
    private List<Shift> shifts;

}
