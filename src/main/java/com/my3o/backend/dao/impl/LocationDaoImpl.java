package com.my3o.backend.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.ILocationDao;
import com.my3o.backend.domain.LocationEntity;
import com.my3o.common.searchbean.LocationSearchBean;

@Repository("locationDao")
public class LocationDaoImpl extends AbstractGenericDao<LocationEntity, String, LocationSearchBean> implements
        ILocationDao {
    @Override
    protected Criteria getExampleCriteria(final LocationSearchBean searchBean) {

        final Criteria criteria = getCriteria();
        if (StringUtils.isNotBlank(searchBean.getKey())) {
            criteria.add(Restrictions.eq(getPKfieldName(), searchBean.getKey()));
        } else {

            if (StringUtils.isNotEmpty(searchBean.getCountryId())) {
                criteria.add(Restrictions.eq("country.id", searchBean.getCountryId()));
            }

            if (StringUtils.isNotEmpty(searchBean.getRegionId())) {
                criteria.add(Restrictions.eq("region.id", searchBean.getRegionId()));
            }

            if (StringUtils.isNotEmpty(searchBean.getDistrictId())) {
                criteria.add(Restrictions.eq("district.id", searchBean.getDistrictId()));
            }

            if (StringUtils.isNotEmpty(searchBean.getTownId())) {
                criteria.add(Restrictions.eq("town.id", searchBean.getTownId()));
            }

            if (StringUtils.isNotEmpty(searchBean.getTownAreaId())) {
                criteria.add(Restrictions.eq("townArea.id", searchBean.getTownAreaId()));
            }
        }
        return criteria;
    }
}