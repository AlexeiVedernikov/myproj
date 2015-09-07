package com.my3o.common.searchbean;

import com.my3o.common.constant.Status;

/**
 * @author Eugene Tuysus 14 июля 2014 г.
 * 
 */
public class TownAreaSearchBean extends AbstractSearchBean<String> {
    private String name;
    private String description;
    private Status status;
    private String townId;

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

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public TownAreaSearchBean(String key) {
        super(key);
    }

    public TownAreaSearchBean() {
        super();
    }
}
