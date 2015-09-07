package com.my3o.frontend.web.model;

import com.my3o.common.constant.UserType;

import java.util.List;

/**
 * @author Eugene Tuysus 14 июля 2014 г.
 * 
 */
public class ContactWebModel {

    private String email;
    private String message;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
