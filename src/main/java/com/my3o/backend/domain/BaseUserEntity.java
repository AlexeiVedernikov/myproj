package com.my3o.backend.domain;

import com.my3o.common.constant.Status;
import com.my3o.common.constant.UserType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * Created by chudoshilkin on 26.09.14.
 */
@MappedSuperclass
public abstract class BaseUserEntity extends BaseComplianceEntity {

    @Column(name = "PASSWORD", length = 32, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "USER_TYPE")
    private UserType userType;

    @Column(name = "LOGIN", length = 128, nullable = false)
    private String login;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
}
