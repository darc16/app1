package com.lessons.model;

import java.util.List;

public class FiltersListDTO {

    private String type;

    private String fieldName;

    private String direction;

    private List<Object> fieldValues;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<Object> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(List<Object> fieldValues) {
        this.fieldValues = fieldValues;
    }
}
