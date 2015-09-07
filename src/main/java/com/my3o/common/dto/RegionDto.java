package com.my3o.common.dto;

import com.my3o.common.constant.Status;

/**
 * Created by chudoshilkin on 10.07.14.
 */
public class RegionDto {

    private String id;
    private String name;
    private String description;
    private Status status;
    private CountryDto countryDto;

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

    public CountryDto getCountryDto() {
        return countryDto;
    }

    public void setCountryDto(CountryDto countryDto) {
        this.countryDto = countryDto;
    }

    @Override
    public String toString() {
        return "RegionDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", countryDto=" + countryDto +
                '}';
    }

    public RegionDto() {
    }

    public RegionDto(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionDto regionDto = (RegionDto) o;

        if (!countryDto.equals(regionDto.countryDto)) return false;
        if (!description.equals(regionDto.description)) return false;
        if (!id.equals(regionDto.id)) return false;
        if (!name.equals(regionDto.name)) return false;
        if (status != regionDto.status) return false;

        return true;
    }

}
