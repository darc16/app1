package com.lessons.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShortReport {

    private Integer id;
    private String description;
    private String displayName;

    public ShortReport(){};

    public ShortReport(int aId, String aDescription, String aDisplayName){
        this.id = aId;
        this.description = aDescription;
        this.displayName = aDisplayName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }




}
