package com.my3o.common.dto;

import com.my3o.common.constant.Status;

/**
 * @author anton
 * 
 */
public class SettingMailDto {
    private String id;
    private String name;
    private String description;
    private Status status;
    private String host;
    private String port;
    private String userName;
    private String userPassword;
    private String defaultSender;
    private String defaultSubjectPrefix;

    private OrganizationDto organizationDto;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getDefaultSender() {
        return defaultSender;
    }

    public String getDefaultSubjectPrefix() {
        return defaultSubjectPrefix;
    }

    public OrganizationDto getOrganizationDto() {
        return organizationDto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setDefaultSender(String defaultSender) {
        this.defaultSender = defaultSender;
    }

    public void setDefaultSubjectPrefix(String defaultSubjectPrefix) {
        this.defaultSubjectPrefix = defaultSubjectPrefix;
    }

    public void setOrganizationDto(OrganizationDto organizationDto) {
        this.organizationDto = organizationDto;
    }

    @Override
    public String toString() {
        return "SettingMailDto [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
                + ", host=" + host + ", port=" + port + ", userName=" + userName + ", userPassword=" + userPassword
                + ", defaultSender=" + defaultSender + ", defaultSubjectPrefix=" + defaultSubjectPrefix
                + ", organizationDto=" + organizationDto + "]";
    }

    public SettingMailDto() {
    }

    public SettingMailDto(String id) {
        this.id = id;
    }
}
