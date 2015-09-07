package com.my3o.common.searchbean;

import com.my3o.common.constant.QiwiStatus;
import com.my3o.common.constant.Status;

import java.util.Date;

/**
 * @author anton
 * 
 */
public class BillUserSearchBean extends AbstractSearchBean<String> {
    private String name;
    private String description;
    private Status status;
    private QiwiStatus qiwiStatus;

    private String userPhone;
    private Double amount;
    private String ccy;
    private String comment;
    private Date lifetime;
    private String paySource;
    private String prvName;
    private String prvId;
    private String userId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public String getUserPhone() {
        return userPhone;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCcy() {
        return ccy;
    }

    public String getComment() {
        return comment;
    }

    public Date getLifetime() {
        return lifetime;
    }

    public String getPaySource() {
        return paySource;
    }

    public String getPrvName() {
        return prvName;
    }

    public String getPrvId() {
        return prvId;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setLifetime(Date lifetime) {
        this.lifetime = lifetime;
    }

    public void setPaySource(String paySource) {
        this.paySource = paySource;
    }

    public void setPrvName(String prvName) {
        this.prvName = prvName;
    }

    public void setPrvId(String prvId) {
        this.prvId = prvId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BillUserSearchBean(String key) {
        super(key);
    }

    public BillUserSearchBean() {
        super();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public QiwiStatus getQiwiStatus() {
        return qiwiStatus;
    }

    public void setQiwiStatus(QiwiStatus qiwiStatus) {
        this.qiwiStatus = qiwiStatus;
    }
}
