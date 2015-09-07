package com.my3o.common.dto;

import com.my3o.common.constant.Status;

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
public class TownDto {
    private String id;
    private String name;
    private String description;
    private Status status;

    private RegionDto regionDto;

    private DistrictDto districtDto;

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

    public DistrictDto getDistrictDto() {
        return districtDto;
    }

    public void setDistrictDto(DistrictDto districtDto) {
        this.districtDto = districtDto;
    }

    @Override
    public String toString() {
        return "TownDto [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
                + ", regionDto=" + regionDto + ", districtDto=" + districtDto + "]";
    }

    public TownDto() {
    }

    public TownDto(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TownDto townDto = (TownDto) o;

        if (!description.equals(townDto.description)) return false;
        if (!(districtDto == null && townDto.districtDto == null)) {
            if (!districtDto.equals(townDto.districtDto)) return false;
        }
        if (!id.equals(townDto.id)) return false;
        if (!name.equals(townDto.name)) return false;
        if (!regionDto.equals(townDto.regionDto)) return false;
        if (status != townDto.status) return false;

        return true;
    }
}
