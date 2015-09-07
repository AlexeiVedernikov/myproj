package com.my3o.common.dto;

import com.my3o.common.constant.Status;

public class CountVisitDto {
    private String id;
    private String name;
    private String description;
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CountVisitDto() {
    }

    public CountVisitDto(String id) {
        this.id = id;
    }
}
