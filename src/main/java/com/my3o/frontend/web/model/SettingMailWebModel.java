package com.my3o.frontend.web.model;

public class SettingMailWebModel extends ReferenceBookModel {

    private String host;
    private String port;
    private String userName;
    private String userPassword;
    private String defaultSender;
    private String defaultSubjectPrefix;

    private String organizationId;

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

    public String getOrganizationId() {
        return organizationId;
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

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

}
