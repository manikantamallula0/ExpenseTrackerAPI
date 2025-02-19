package com.demo.expensetrackerapi.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {
    private Integer stausCode;
    private String message;
    private Date timeStamp;

}
