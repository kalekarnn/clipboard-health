package com.clipboard.healthcare.common;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ErrorMessage {
    private int statusCode;
    private int errorCode;
    private Date timestamp;
    private String message;
    private String description;
}
