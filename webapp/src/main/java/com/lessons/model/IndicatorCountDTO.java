package com.lessons.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IndicatorCountDTO {

    public IndicatorCountDTO (Integer total_records){
        this.total_records = total_records;
    }

    public int getTotal_records() {
        return total_records;
    }

    public void setTotal_records(Integer total_records) {
        this.total_records = total_records;
    }

    @JsonProperty("total")
    private int total_records;
}
