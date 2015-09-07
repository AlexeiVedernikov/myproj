package com.my3o.backend.domain;

import com.my3o.common.constant.Status;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
@Entity
@Table(name = "DISCIPLINE")
@AttributeOverride(name = "id", column = @Column(name = "DISCIPLINE_ID", length = 32))
public class DisciplineEntity extends BaseReferenceBookEntity {


}
