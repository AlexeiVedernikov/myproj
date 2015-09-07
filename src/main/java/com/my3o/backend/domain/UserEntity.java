package com.my3o.backend.domain;

import com.my3o.common.constant.Status;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
@Entity
@Table(name = "USERS")
@AttributeOverride(name = "id", column = @Column(name = "USER_ID", length = 32))
public class UserEntity extends BaseUserEntity {

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "ORGANIZATION_USER", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ORGANIZATION_ID") })
    @Fetch(FetchMode.SUBSELECT)
    private Set<OrganizationEntity> organizationEntitySet = new HashSet<OrganizationEntity>(0);

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "GROUP_USER", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "GROUP_ID") })
    @Fetch(FetchMode.SUBSELECT)
    private Set<GroupEntity> groupEntitySet = new HashSet<GroupEntity>(0);

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<NotificationEntity> notificationEntitySet;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<BillUserEntity> billUserEntitySet;

    @Column(name = "NAME", length = 512, nullable = false)
    private String name;

    @Column(name = "EMAIL", length = 128, nullable = false)
    private String email;

    @Column(name = "PHONE", length = 32, nullable = true)
    private String phone;

    @Column(name = "DESCRIPTION", length = 512, nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private Status status;

    @Column(nullable = false, name = "IS_FAKE")
    @Type(type = "yes_no")
    private Boolean isFake = false;

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_PARENT", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "PARENT_USER_ID") })
    @Fetch(FetchMode.SUBSELECT)
    private Set<UserEntity> parentSet = new HashSet<UserEntity>(0);

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_PARENT", joinColumns = { @JoinColumn(name = "PARENT_USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
    @Fetch(FetchMode.SUBSELECT)
    private Set<UserEntity> childrenSet = new HashSet<UserEntity>(0);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Set<OrganizationEntity> getOrganizationEntitySet() {
        return organizationEntitySet;
    }

    public void setOrganizationEntitySet(Set<OrganizationEntity> organizationEntitySet) {
        this.organizationEntitySet = organizationEntitySet;
    }

    public Set<GroupEntity> getGroupEntitySet() {
        return groupEntitySet;
    }

    public void setGroupEntitySet(Set<GroupEntity> groupEntitySet) {
        this.groupEntitySet = groupEntitySet;
    }

    public Set<UserEntity> getParentSet() {
        return parentSet;
    }

    public void setParentSet(Set<UserEntity> parentSet) {
        this.parentSet = parentSet;
    }

    public Set<UserEntity> getChildrenSet() {
        return childrenSet;
    }

    public void setChildrenSet(Set<UserEntity> childrenSet) {
        this.childrenSet = childrenSet;
    }

    public Set<NotificationEntity> getNotificationEntitySet() {
        return notificationEntitySet;
    }

    public void setNotificationEntitySet(Set<NotificationEntity> notificationEntitySet) {
        this.notificationEntitySet = notificationEntitySet;
    }

    public Set<BillUserEntity> getBillUserEntitySet() {
        return billUserEntitySet;
    }

    public void setBillUserEntitySet(Set<BillUserEntity> billUserEntitySet) {
        this.billUserEntitySet = billUserEntitySet;
    }

}
