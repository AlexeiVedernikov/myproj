package com.my3o.frontend.web.model;

public class OrganizationWebModel extends ReferenceBookModel {

    private String phone;
    private String address;

    private String countryId;
    private String regionId;
    private String districtId;
    private String townId;
    private String townAreaId;

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
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

}
