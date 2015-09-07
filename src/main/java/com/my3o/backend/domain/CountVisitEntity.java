package com.my3o.backend.domain;

import javax.persistence.*;

@Entity
@Table(name = "COUNT_VISIT")
@AttributeOverride(name = "id", column = @Column(name = "COUNT_VISIT_ID", length = 32))
public class CountVisitEntity extends BaseReferenceBookEntity {

}
