package com.my3o.frontend.web.model;

import com.my3o.common.constant.UserType;

import java.util.List;

/**
 * @author Eugene Tuysus 14 июля 2014 г.
 * 
 */
public class UserWebModel extends ReferenceBookModel {

    private String email;
    private String password;
    private String phone;
    private String organizationId;
    private Boolean isFake;
    private List<String> organizationIdList;
    private String groupId;
    private UserType userType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getOrganizationIdList() {
        return organizationIdList;
    }

    public void setOrganizationIdList(List<String> organizationIdList) {
        this.organizationIdList = organizationIdList;
    }

    public Boolean getIsFake() {
        return isFake;
    }

    public void setIsFake(Boolean isFake) {
        this.isFake = isFake;
    }
}
