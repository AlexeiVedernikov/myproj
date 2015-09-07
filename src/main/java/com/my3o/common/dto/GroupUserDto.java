package com.my3o.common.dto;

import java.util.Date;

/**
 * @author Eugene Tuysus 21 июля 2014 г.
 * 
 */
public class GroupUserDto {
    private String id;
    private GroupDto groupDto;
    private UserDto userDto;
    private Date dateStart;
    private Date dateEnd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GroupDto getGroupDto() {
        return groupDto;
    }

    public void setGroupDto(GroupDto groupDto) {
        this.groupDto = groupDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "GroupUserDto [id=" + id + ", groupDto=" + groupDto + ", userDto=" + userDto + ", dateStart="
                + dateStart + ", dateEnd=" + dateEnd + "]";
    }

    public GroupUserDto() {
    }

    public GroupUserDto(String id) {
        this.id = id;
    }

}
