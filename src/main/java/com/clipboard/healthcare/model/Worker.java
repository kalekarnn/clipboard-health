package com.clipboard.healthcare.model;

import com.clipboard.healthcare.common.Profession;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Worker {
    private int id;
    private String name;
    private boolean isActive;
    private Profession profession;
}
