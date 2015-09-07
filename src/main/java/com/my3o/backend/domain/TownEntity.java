package com.my3o.backend.domain;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TOWN")
@AttributeOverride(name = "id", column = @Column(name = "TOWN_ID", length = 32))
public class TownEntity extends BaseReferenceBookEntity {

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "REGION_ID", insertable = true, updatable = true, nullable = true)
    private RegionEntity region;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "DISTRICT_ID", insertable = true, updatable = true, nullable = true)
    private DistrictEntity district;

    @OneToMany(mappedBy = "town", fetch = FetchType.LAZY)
    private Set<TownAreaEntity> townAreaEntitySet;

    // @OneToMany(mappedBy = "town")
    // private Set<OrganizationEntity> organizationEntitySet;

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }

    public DistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(DistrictEntity district) {
        this.district = district;
    }

    public Set<TownAreaEntity> getTownAreaEntitySet() {
        return townAreaEntitySet;
    }

    public void setTownAreaEntitySet(Set<TownAreaEntity> townAreaEntitySet) {
        this.townAreaEntitySet = townAreaEntitySet;
    }

}
