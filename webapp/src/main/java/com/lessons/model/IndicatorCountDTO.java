package com.lessons.model;

public class IndicatorCountDTO {

    public IndicatorCountDTO (Integer total_records){
        this.total_records = total_records;
    }

    public Integer getTotal_records() {
        return total_records;
    }

    public void setTotal_records(Integer total_records) {
        this.total_records = total_records;
    }

    private Integer total_records;
}
