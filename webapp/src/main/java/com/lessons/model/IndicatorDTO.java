package com.lessons.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class IndicatorDTO {
    private int id;
    private String value;
    @JsonProperty("createdDate")
    private Timestamp created_date;
    private String created_by;

    public IndicatorDTO(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }


}
