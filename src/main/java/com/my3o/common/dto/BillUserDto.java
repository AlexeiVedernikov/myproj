package com.my3o.common.dto;

import java.util.Date;

import com.my3o.common.constant.QiwiStatus;
import com.my3o.common.constant.Status;

/**
 * @author anton
 * 
 */
public class BillUserDto {
    private String id;
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
    private UserDto userDto;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
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

    public UserDto getUserDto() {
        return userDto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public QiwiStatus getQiwiStatus() {
        return qiwiStatus;
    }

    public void setQiwiStatus(QiwiStatus qiwiStatus) {
        this.qiwiStatus = qiwiStatus;
    }

    @Override
    public String toString() {
        return "BillUserDto [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
                + ", qiwiStatus=" + qiwiStatus + ", userPhone=" + userPhone + ", amount=" + amount + ", ccy=" + ccy
                + ", comment=" + comment + ", lifetime=" + lifetime + ", paySource=" + paySource + ", prvName="
                + prvName + ", prvId=" + prvId + ", userDto=" + userDto + "]";
    }

    public BillUserDto() {
    }

    public BillUserDto(String id) {
        this.id = id;
    }

}
