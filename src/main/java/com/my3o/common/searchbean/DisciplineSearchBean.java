package com.my3o.common.searchbean;

import com.my3o.common.constant.Status;
import com.my3o.common.dto.OrganizationDto;

import java.util.List;

/**
 * Created by andrew
 */
public class DisciplineSearchBean extends BaseUserSearchBean {
    private String name;
    private String description;
    private Status status;

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

    @Override
    public String toString() {
        return "DisciplineSearchBean{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    public DisciplineSearchBean(String key) {
        super(key);
    }

    public DisciplineSearchBean() {
        super();
    }

}
