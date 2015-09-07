package com.my3o.frontend.web.model;

/**
 * @author Eugene Tuysus 21 июля 2014 г.
 * 
 */
public class GroupWebModel extends ReferenceBookModel {

    private String number;

    private String organizationId;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

}
