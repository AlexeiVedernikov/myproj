package com.my3o.backend.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by chudoshilkin on 10.07.14.
 */

@Entity
@Table(name = "DISTRICT")
@AttributeOverride(name = "id", column = @Column(name = "DISTRICT_ID", length = 32))
public class DistrictEntity extends BaseReferenceBookEntity {

    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch= FetchType.LAZY)
    @JoinColumn(name = "REGION_ID", insertable = true, updatable = true, nullable=true)
    private RegionEntity region;

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    private Set<TownEntity> townEntitySet;

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }

    public Set<TownEntity> getTownEntitySet() {
        return townEntitySet;
    }

    public void setTownEntitySet(Set<TownEntity> townEntitySet) {
        this.townEntitySet = townEntitySet;
    }

}
