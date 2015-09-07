package com.my3o.common.searchbean;

import com.my3o.common.constant.Status;

/**
 * @author anton
 * 
 */
public class SettingMailSearchBean extends AbstractSearchBean<String> {

    private String name;
    private String description;
    private Status status;
    private String host;
    private String port;
    private String userName;
    private String userPassword;
    private String defaultSender;
    private String defaultSubjectPrefix;
    private String organizationId;

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

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

}
