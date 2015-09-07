package com.my3o.common.dto;

import com.my3o.common.constant.Status;

/**
 * Created by chudoshilkin on 10.07.14.
 */
public class DistrictDto {

    private String id;
    private String name;
    private String description;
    private Status status;
    private RegionDto regionDto;

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

    public RegionDto getRegionDto() {
        return regionDto;
    }

    public void setRegionDto(RegionDto regionDto) {
        this.regionDto = regionDto;
    }

    @Override
    public String toString() {
        return "DistrictDto{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", description='" + description + '\''
                + ", status=" + status + ", regionDto=" + regionDto + '}';
    }

    public DistrictDto() {
    }

    public DistrictDto(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistrictDto that = (DistrictDto) o;

        if (!description.equals(that.description)) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!regionDto.equals(that.regionDto)) return false;
        if (status != that.status) return false;

        return true;
    }

}
