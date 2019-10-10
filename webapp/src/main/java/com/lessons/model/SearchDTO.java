package com.lessons.model;

import java.util.List;

public class SearchDTO {

    private List<String> filters;
    private String rawQuery;

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public String getRawQuery() {
        return rawQuery;
    }

    public void setRawQuery(String rawQuery) {
        this.rawQuery = rawQuery;
    }




}
