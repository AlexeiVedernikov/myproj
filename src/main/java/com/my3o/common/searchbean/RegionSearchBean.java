package com.my3o.common.searchbean;

import com.my3o.common.constant.Status;

/**
 * Created by chudoshilkin on 10.07.14.
 */
public class RegionSearchBean extends AbstractSearchBean<String>{
    private String name;
    private String description;
    private Status status;
    private String countryId;

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

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public RegionSearchBean(String key) {
        super(key);
    }

    public RegionSearchBean() {
        super();
    }
}
