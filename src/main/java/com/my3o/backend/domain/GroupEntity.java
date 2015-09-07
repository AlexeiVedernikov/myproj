package com.my3o.backend.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.my3o.common.constant.Status;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "GROUPS")
@AttributeOverride(name = "id", column = @Column(name = "GROUP_ID", length = 32))
public class GroupEntity extends BaseComplianceEntity {

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID", insertable = true, updatable = true, nullable = false)
    private OrganizationEntity organization;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private Set<NotificationEntity> notificationEntitySet;

    @Column(nullable = false, length = 128, name = "NAME")
    private String name;

    @Column(nullable = false, name = "NUMBER")
    private String number;

    @Column(nullable = true, length = 255, name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public Set<NotificationEntity> getNotificationEntitySet() {
        return notificationEntitySet;
    }

    public void setNotificationEntitySet(Set<NotificationEntity> notificationEntitySet) {
        this.notificationEntitySet = notificationEntitySet;
    }

}
