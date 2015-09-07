package com.my3o.backend.domain;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author anton
 * 
 */
@Entity
@Table(name = "SETTINGS_MAIL")
@AttributeOverride(name = "id", column = @Column(name = "SETTINGS_MAIL_ID", length = 32))
public class SettingMailEntity extends BaseReferenceBookEntity {

    @Column(nullable = true, name = "HOST")
    private String host;

    @Column(nullable = true, name = "PORT")
    private String port;

    @Column(nullable = true, name = "USER_NAME")
    private String userName;

    @Column(nullable = true, name = "USER_PASSWORD")
    private String userPassword;

    @Column(nullable = true, name = "DEFAULT_SENDER")
    private String defaultSender;

    @Column(nullable = true, name = "DEFAULT_SUBJECT_PREFIX")
    private String defaultSubjectPrefix;

    @OneToMany(mappedBy = "settingMail")
    private Set<NotificationEntity> notificationEntitySet;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID", insertable = true, updatable = true, nullable = false)
    private OrganizationEntity organization;

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

    public OrganizationEntity getOrganization() {
        return organization;
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

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

}
