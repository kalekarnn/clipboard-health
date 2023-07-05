package com.clipboard.healthcare.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Document {
    private int id;
    private String name;
    private boolean isActive;
}
