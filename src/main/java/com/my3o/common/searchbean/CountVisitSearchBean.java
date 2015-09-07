package com.my3o.common.searchbean;

import com.my3o.common.constant.Status;

public class CountVisitSearchBean extends AbstractSearchBean<String> {
    private String name;
    private String description;
    private Status status;

    public String getName() {
        return name;
    }

    public CountVisitSearchBean(String key) {
        super(key);
    }

    public CountVisitSearchBean() {
        super();
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
}
