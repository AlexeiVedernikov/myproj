package com.my3o.backend.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.my3o.common.constant.QiwiStatus;

@Entity
@Table(name = "BILL_USER")
@AttributeOverride(name = "id", column = @Column(name = "BILL_USER_ID", length = 32))
public class BillUserEntity extends BaseReferenceBookEntity {

    @Column(nullable = true, length = 32, name = "USER_PHONE")
    private String userPhone;

    @Column(nullable = true, name = "AMOUNT")
    private Double amount;

    @Column(nullable = true, length = 32, name = "CCY")
    private String ccy;

    @Column(nullable = true, length = 256, name = "COMMENT")
    private String comment;

    @Column(nullable = true, name = "LIFETIME")
    private Date lifetime;

    @Column(nullable = true, length = 32, name = "PAY_SOURCE")
    private String paySource;

    @Column(nullable = true, length = 32, name = "PRV_NAME")
    private String prvName;

    @Column(nullable = true, length = 32, name = "PRV_ID")
    private String prvId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS_BILL")
    private QiwiStatus qiwiStatus;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = true, updatable = true, nullable = false)
    private UserEntity user;

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

    public UserEntity getUser() {
        return user;
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

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public QiwiStatus getQiwiStatus() {
        return qiwiStatus;
    }

    public void setQiwiStatus(QiwiStatus qiwiStatus) {
        this.qiwiStatus = qiwiStatus;
    }

}
