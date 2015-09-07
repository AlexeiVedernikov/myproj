package com.my3o.backend.dao.impl;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.IDistrictDao;
import com.my3o.backend.domain.DistrictEntity;
import com.my3o.common.searchbean.DistrictSearchBean;
import com.my3o.common.searchbean.RegionSearchBean;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by chudoshilkin on 10.07.14.
 */
@Repository("districtDao")
public class DistrictDaoImpl extends AbstractGenericDao<DistrictEntity, String, DistrictSearchBean> implements IDistrictDao {

    @Override
    protected Criteria getExampleCriteria(final DistrictSearchBean searchBean) {

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
        }

        return criteria;
    }
}
