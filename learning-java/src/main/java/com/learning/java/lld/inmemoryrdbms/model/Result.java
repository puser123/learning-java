package com.learning.java.lld.inmemoryrdbms.model;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private boolean isSuccess;
    // This needs to be generic , row is specific to one table right now
    private List<Row> rows;
}
