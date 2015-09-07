package com.my3o.common.searchbean;

import com.my3o.common.constant.Status;
import com.my3o.common.constant.UserType;
import com.my3o.common.dto.OrganizationDto;

import java.util.List;

/**
 * Created by chudoshilkin on 26.09.14.
 */
public class BaseUserSearchBean extends AbstractSearchBean<String> {
    private UserType userType;
    private String login;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public BaseUserSearchBean(String key) {
        super(key);
    }

    public BaseUserSearchBean() {
        super();
    }
}
