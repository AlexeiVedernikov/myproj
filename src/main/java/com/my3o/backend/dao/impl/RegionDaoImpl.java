package com.my3o.backend.dao.impl;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.IRegionDao;
import com.my3o.backend.domain.RegionEntity;
import com.my3o.common.searchbean.RegionSearchBean;
import com.my3o.common.searchbean.SearchBean;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * Created by chudoshilkin on 10.07.14.
 */
@Repository("regionDao")
public class RegionDaoImpl extends AbstractGenericDao<RegionEntity, String, RegionSearchBean> implements IRegionDao {

    @Override
    protected Criteria getExampleCriteria(final RegionSearchBean searchBean) {

        final Criteria criteria = getCriteria();
        if(searchBean.hasMultipleKeys()){
            criteria.add(Restrictions.in(getPKfieldName(), searchBean.getKeys()));
        } else if (StringUtils.isNotBlank(searchBean.getKey())) {
            criteria.add(Restrictions.eq(getPKfieldName(), searchBean.getKey()));
        } else {

            if (StringUtils.isNotEmpty(searchBean.getName())) {
                criteria.add(Restrictions.eq("name", searchBean.getName()));
            }

            if (StringUtils.isNotEmpty(searchBean.getDescription())) {
                criteria.add(Restrictions.eq("description", searchBean.getDescription()));
            }

            if (searchBean.getStatus() != null) {
                criteria.add(Restrictions.eq("status", searchBean.getStatus()));
            }

            if (StringUtils.isNotEmpty(searchBean.getCountryId())) {
                criteria.add(Restrictions.eq("country.id", searchBean.getCountryId()));
            }
        }

        return criteria;
    }

}
