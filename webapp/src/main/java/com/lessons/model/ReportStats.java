package com.lessons.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportStats {

    private String displayName;

    @JsonProperty("totalIndicators")
    private Integer indicatorCount;
    private Integer reportId;


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getIndicatorCount() {
        return indicatorCount;
    }

    public void setIndicatorCount(Integer indicatorCount) {
        this.indicatorCount = indicatorCount;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }


}
