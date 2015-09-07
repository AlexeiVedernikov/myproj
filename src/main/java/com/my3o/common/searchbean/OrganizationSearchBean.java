package com.my3o.common.searchbean;

import com.my3o.common.constant.Status;

public class OrganizationSearchBean extends AbstractSearchBean<String> {
    private String organizationId;
    private String organizationName;
    private String organizationDescription;
    private String organizationStatus;
    private String organizationPhone;
    private String organizationAddress;
    private Status status;

    private String locationId;
    private String countryId;
    private String regionId;
    private String districtId;
    private String townId;
    private String townAreaId;

    public String getOrganizationName() {
        return organizationName;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public String getOrganizationStatus() {
        return organizationStatus;
    }

    public String getOrganizationPhone() {
        return organizationPhone;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getRegionId() {
        return regionId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public String getTownId() {
        return townId;
    }

    public String getTownAreaId() {
        return townAreaId;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }

    public void setOrganizationStatus(String organizationStatus) {
        this.organizationStatus = organizationStatus;
    }

    public void setOrganizationPhone(String organizationPhone) {
        this.organizationPhone = organizationPhone;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public void setTownAreaId(String townAreaId) {
        this.townAreaId = townAreaId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
