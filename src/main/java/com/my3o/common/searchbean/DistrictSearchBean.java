package com.my3o.common.searchbean;

import com.my3o.common.constant.Status;

/**
 * Created by chudoshilkin on 11.07.14.
 */
public class DistrictSearchBean extends AbstractSearchBean<String> {
    private String name;
    private String description;
    private Status status;
    private String regionId;

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

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public DistrictSearchBean(String key) {
        super(key);
    }

    public DistrictSearchBean() {
        super();
    }
}
