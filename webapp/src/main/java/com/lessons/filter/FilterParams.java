package com.lessons.filter;

import java.util.Map;

public class FilterParams {

    private String sql;
    private Map<String, Object> bindVars;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<String, Object> getBindVars() {
        return bindVars;
    }

    public void setBindVars(Map<String, Object> bindVars) {
        this.bindVars = bindVars;
    }

}
