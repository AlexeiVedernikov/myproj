package com.my3o.common.dto;

import com.my3o.common.constant.Status;
import com.my3o.common.constant.UserType;

import java.util.Date;
import java.util.List;

/**
 * Created by: andrew
 */
public class UserDto {
    private String id;
    private String name;
    private String description;
    private String email;
    private String password;
    private String phone;
    private UserType userType;
    private Status status;
    private Boolean isFake;
    private String login;

    private OrganizationDto organizationDto;
    private GroupDto groupDto;

    private Date lastUpdateTime;
    private String updateByUserId;
    private Date createTime;
    private String createByUserId;
    private List<UserDto> userList;
    private List<OrganizationDto> organizationList;
    private List<GroupDto> groupList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Boolean getIsFake() {
        return isFake;
    }

    public void setIsFake(Boolean isFake) {
        this.isFake = isFake;
    }

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

    public OrganizationDto getOrganizationDto() {
        return organizationDto;
    }

    public void setOrganizationDto(OrganizationDto organizationDto) {
        this.organizationDto = organizationDto;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<UserDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDto> userList) {
        this.userList = userList;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public List<OrganizationDto> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<OrganizationDto> organizationList) {
        this.organizationList = organizationList;
    }

    public List<GroupDto> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupDto> groupList) {
        this.groupList = groupList;
    }

    public GroupDto getGroupDto() {
        return groupDto;
    }

    public void setGroupDto(GroupDto groupDto) {
        this.groupDto = groupDto;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", userType=" + userType +
                ", status=" + status +
                ", isFake=" + isFake +
                ", login='" + login + '\'' +
                ", organizationDto=" + organizationDto +
                ", groupDto=" + groupDto +
                ", lastUpdateTime=" + lastUpdateTime +
                ", updateByUserId='" + updateByUserId + '\'' +
                ", createTime=" + createTime +
                ", createByUserId='" + createByUserId + '\'' +
                ", userList=" + userList +
                ", organizationList=" + organizationList +
                ", groupList=" + groupList +
                '}';
    }

    public UserDto() {
    }

    public UserDto(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (id != null ? !id.equals(userDto.id) : userDto.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
