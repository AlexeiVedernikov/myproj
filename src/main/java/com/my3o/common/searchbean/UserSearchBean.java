package com.my3o.common.searchbean;

import com.my3o.common.constant.Status;
import com.my3o.common.dto.OrganizationDto;

import java.util.List;

/**
 * Created by andrew
 */
public class UserSearchBean extends BaseUserSearchBean {
    private String name;
    private String description;
    private String email;
    private String phone;
    private Status status;
    private Boolean isFake;
    private String groupId;
    private String organizationId;
//    private String parentId;
    private String childrenId;
    private List<OrganizationDto> organizationList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getIsFake() {
        return isFake;
    }

    public void setIsFake(Boolean isFake) {
        this.isFake = isFake;
    }

    public List<OrganizationDto> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<OrganizationDto> organizationList) {
        this.organizationList = organizationList;
    }

    public String getChildrenId() {
        return childrenId;
    }

    public void setChildrenId(String childrenId) {
        this.childrenId = childrenId;
    }

    public UserSearchBean(String key) {
        super(key);
    }

    public UserSearchBean() {
        super();
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}
