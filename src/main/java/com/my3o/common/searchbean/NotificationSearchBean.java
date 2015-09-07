package com.my3o.common.searchbean;

import com.my3o.common.constant.NotificationType;
import com.my3o.common.constant.NotificationUserType;
import com.my3o.common.constant.Status;

/**
 * @author anton
 * 
 */
public class NotificationSearchBean extends AbstractSearchBean<String> {
    private String name;
    private String description;
    private NotificationType notificationType;
    private Status status;
    private NotificationUserType userType;
    private String settingMailId;
    private String userId;
    private String groupId;
    private String organizationId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public Status getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getSettingMailId() {
        return settingMailId;
    }

    public void setSettingMailId(String settingMailId) {
        this.settingMailId = settingMailId;
    }

    public NotificationUserType getUserType() {
        return userType;
    }

    public void setUserType(NotificationUserType userType) {
        this.userType = userType;
    }
}
