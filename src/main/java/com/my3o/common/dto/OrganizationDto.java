package com.my3o.common.dto;

import com.my3o.common.constant.Status;

public class OrganizationDto {

    private String id;
    private String name;
    private String description;
    private Status status;
    private String phone;
    private String address;

    private LocationDto locationDto;

    // private CountryDto countryDto;
    // private RegionDto regionDto;
    // private DistrictDto districtDto;
    // private TownDto townDto;
    // private TownAreaDto townAreaDto;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // public LocationDto getLocationDto() {
    // return locationDto;
    // }
    //
    // public CountryDto getCountryDto() {
    // return countryDto;
    // }
    //
    // public RegionDto getRegionDto() {
    // return regionDto;
    // }
    //
    // public DistrictDto getDistrictDto() {
    // return districtDto;
    // }
    //
    // public TownDto getTownDto() {
    // return townDto;
    // }
    //
    // public TownAreaDto getTownAreaDto() {
    // return townAreaDto;
    // }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // public void setLocationDto(LocationDto locationDto) {
    // this.locationDto = locationDto;
    // }
    //
    // public void setCountryDto(CountryDto countryDto) {
    // this.countryDto = countryDto;
    // }
    //
    // public void setRegionDto(RegionDto regionDto) {
    // this.regionDto = regionDto;
    // }
    //
    // public void setDistrictDto(DistrictDto districtDto) {
    // this.districtDto = districtDto;
    // }
    //
    // public void setTownDto(TownDto townDto) {
    // this.townDto = townDto;
    // }
    //
    // public void setTownAreaDto(TownAreaDto townAreaDto) {
    // this.townAreaDto = townAreaDto;
    // }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocationDto getLocationDto() {
        return locationDto;
    }

    public void setLocationDto(LocationDto locationDto) {
        this.locationDto = locationDto;
    }

    @Override
    public String toString() {
        return "OrganizationDto [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
                + ", phone=" + phone + ", address=" + address + ", locationDto=" + locationDto + "]";
    }

    public OrganizationDto() {
    }

    public OrganizationDto(String id) {
        this.id = id;
    }

}
