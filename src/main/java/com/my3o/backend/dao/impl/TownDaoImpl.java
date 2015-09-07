package com.my3o.backend.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.ITownDao;
import com.my3o.backend.domain.TownEntity;
import com.my3o.common.searchbean.TownSearchBean;

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
@Repository("townDao")
public class TownDaoImpl extends AbstractGenericDao<TownEntity, String, TownSearchBean> implements ITownDao {

    @Override
    protected Criteria getExampleCriteria(final TownSearchBean searchBean) {

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

            if (StringUtils.isNotEmpty(searchBean.getRegionId())) {
                criteria.add(Restrictions.eq("region.id", searchBean.getRegionId()));
            }

            if (StringUtils.isNotEmpty(searchBean.getDistrictId())) {
                criteria.add(Restrictions.eq("district.id", searchBean.getDistrictId()));
            }
        }

        return criteria;
    }
}
