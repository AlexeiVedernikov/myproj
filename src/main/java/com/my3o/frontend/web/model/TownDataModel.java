package com.my3o.frontend.web.model;

public class TownDataModel extends ReferenceBookModel {

    private String regionId;
    private String districtId;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

}
