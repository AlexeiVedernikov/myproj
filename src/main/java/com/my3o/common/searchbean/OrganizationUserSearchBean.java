package com.my3o.common.searchbean;

/**
 * @author Eugene Tuysus 17 июля 2014 г.
 * 
 */
public class OrganizationUserSearchBean extends AbstractSearchBean<String> {
    private String organizationUserId;
    private long dateStart;
    private long dateEnd;
    private String orgnizationId;
    private String userId;

    public String getOrganizationUserId() {
        return organizationUserId;
    }

    public void setOrganizationUserId(String organizationUserId) {
        this.organizationUserId = organizationUserId;
    }

    public long getDateStart() {
        return dateStart;
    }

    public void setDateStart(long dateStart) {
        this.dateStart = dateStart;
    }

    public long getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(long dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getOrgnizationId() {
        return orgnizationId;
    }

    public void setOrgnizationId(String orgnizationId) {
        this.orgnizationId = orgnizationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
