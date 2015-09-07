package com.my3o.backend.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "ORGANIZATION")
@AttributeOverride(name = "id", column = @Column(name = "ORGANIZATION_ID", length = 32))
public class OrganizationEntity extends BaseReferenceBookEntity {

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private Set<GroupEntity> GroupEntitySet;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private Set<NotificationEntity> notificationEntitySet;

    @Column(nullable = true, length = 128, name = "PHONE")
    private String phone;

    @Column(nullable = true, length = 255, name = "ADDRESS")
    private String address;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID", insertable = true, updatable = true, nullable = true)
    private LocationEntity location;

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public Set<GroupEntity> getGroupEntitySet() {
        return GroupEntitySet;
    }

    public void setGroupEntitySet(Set<GroupEntity> groupEntitySet) {
        GroupEntitySet = groupEntitySet;
    }

    public Set<NotificationEntity> getNotificationEntitySet() {
        return notificationEntitySet;
    }

    public void setNotificationEntitySet(Set<NotificationEntity> notificationEntitySet) {
        this.notificationEntitySet = notificationEntitySet;
    }

}
