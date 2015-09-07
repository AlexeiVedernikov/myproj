package com.my3o.backend.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.ICountryDao;
import com.my3o.backend.domain.CountryEntity;
import com.my3o.common.searchbean.CountrySearchBean;

/**
 * @author Eugene Tuysus 14 июля 2014 г.
 * 
 */
@Repository("countryDao")
public class CountryDaoImpl extends AbstractGenericDao<CountryEntity, String, CountrySearchBean> implements ICountryDao {

    @Override
    protected Criteria getExampleCriteria(final CountrySearchBean searchBean) {

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

        }

        return criteria;
    }
}
