package com.lessons.models;

import java.util.List;

public class AddReportDTO {
    private String name;
    private List<Integer> priority;
    private Integer reportType;
    private List<Integer> reportSources;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPriority() {
        return priority;
    }

    public void setPriority(List<Integer> priority) {
        this.priority = priority;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public List<Integer> getReportSources() {
        return reportSources;
    }

    public void setReportSources(List<Integer> reportSources) {
        this.reportSources = reportSources;
    }
}