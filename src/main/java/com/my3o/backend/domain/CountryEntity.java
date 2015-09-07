package com.my3o.backend.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "COUNTRY")
@AttributeOverride(name = "id", column = @Column(name = "COUNTRY_ID", length = 32))
public class CountryEntity extends BaseReferenceBookEntity {

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<RegionEntity> regionEntitySet;

    public Set<RegionEntity> getRegionEntitySet() {
        return regionEntitySet;
    }

    public void setRegionEntitySet(Set<RegionEntity> regionEntitySet) {
        this.regionEntitySet = regionEntitySet;
    }
}
