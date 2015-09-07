package com.my3o.backend.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "REGION")
@AttributeOverride(name = "id", column = @Column(name = "REGION_ID", length = 32))
public class RegionEntity extends BaseReferenceBookEntity {

    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch= FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID", insertable = true, updatable = true, nullable=true)
    private CountryEntity country;

    @OneToMany(mappedBy = "region")
    private Set<DistrictEntity> districtEntitySet;

    @OneToMany(mappedBy = "region")
    private Set<TownEntity> townEntitySet;

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public Set<DistrictEntity> getDistrictEntitySet() {
        return districtEntitySet;
    }

    public void setDistrictEntitySet(Set<DistrictEntity> districtEntitySet) {
        this.districtEntitySet = districtEntitySet;
    }

    public Set<TownEntity> getTownEntitySet() {
        return townEntitySet;
    }

    public void setTownEntitySet(Set<TownEntity> townEntitySet) {
        this.townEntitySet = townEntitySet;
    }
}
