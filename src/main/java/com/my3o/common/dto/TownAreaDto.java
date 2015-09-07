package com.my3o.common.dto;

import com.my3o.common.constant.Status;

/**
 * @author Eugene Tuysus 14 июля 2014 г.
 * 
 */
public class TownAreaDto {
    private String id;
    private String name;
    private String description;
    private Status status;
    private TownDto townDto;

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

    public TownDto getTownDto() {
        return townDto;
    }

    public void setTownDto(TownDto townDto) {
        this.townDto = townDto;
    }

    @Override
    public String toString() {
        return "TownAreaDto [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
                + ", townDto=" + townDto + "]";
    }

    public TownAreaDto() {
    }

    public TownAreaDto(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TownAreaDto that = (TownAreaDto) o;

        if (!description.equals(that.description)) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (status != that.status) return false;
        if (!townDto.equals(that.townDto)) return false;

        return true;
    }
}
