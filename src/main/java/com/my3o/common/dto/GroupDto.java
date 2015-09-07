package com.my3o.common.dto;

import java.util.Date;

import com.my3o.common.constant.Status;

/**
 * @author Eugene Tuysus 21 июля 2014 г.
 * 
 */
public class GroupDto {
    private String id;
    private OrganizationDto organizationDto;
    private String name;
    private String number;
    private String description;
    private Status status;
    private Date lastUpdateTime;
    private String updateByUserId;
    private Date createTime;
    private String createByUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrganizationDto getOrganizationDto() {
        return organizationDto;
    }

    public void setOrganizationDto(OrganizationDto organizationDto) {
        this.organizationDto = organizationDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getUpdateByUserId() {
        return updateByUserId;
    }

    public void setUpdateByUserId(String updateByUserId) {
        this.updateByUserId = updateByUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateByUserId() {
        return createByUserId;
    }

    public void setCreateByUserId(String createByUserId) {
        this.createByUserId = createByUserId;
    }

    @Override
    public String toString() {
        return "GroupDto [id=" + id + ", organizationDto=" + organizationDto + ", name=" + name + ", number=" + number
                + ", description=" + description + ", status=" + status + ", lastUpdateTime=" + lastUpdateTime
                + ", updateByUserId=" + updateByUserId + ", createTime=" + createTime + ", createByUserId="
                + createByUserId + "]";
    }

    public GroupDto() {
    }

    public GroupDto(String id) {
        this.id = id;
    }
}
