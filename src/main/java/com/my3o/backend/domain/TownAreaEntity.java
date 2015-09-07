package com.my3o.backend.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Eugene Tuysus 14 июля 2014 г.
 * 
 */
@Entity
@Table(name = "TOWN_AREA")
@AttributeOverride(name = "id", column = @Column(name = "TOWN_AREA_ID", length = 32))
public class TownAreaEntity extends BaseReferenceBookEntity {

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "TOWN_ID", insertable = true, updatable = true, nullable = true)
    private TownEntity town;

    // @OneToMany(mappedBy = "townArea")
    // private Set<LocationEntity> locationEntitySet;

    public TownEntity getTown() {
        return town;
    }

    public void setTown(TownEntity town) {
        this.town = town;
    }

}
