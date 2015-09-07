package com.my3o.common.dto;

import com.my3o.common.constant.Status;

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
public class CountryDto {
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

    @Override
    public String toString() {
        return "CountryDto [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status + "]";
    }

    public CountryDto() {
    }

    public CountryDto(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryDto that = (CountryDto) o;

        if (!description.equals(that.description)) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (status != that.status) return false;

        return true;
    }

}
