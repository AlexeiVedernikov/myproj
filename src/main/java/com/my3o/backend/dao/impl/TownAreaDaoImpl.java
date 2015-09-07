package com.my3o.backend.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.ITownAreaDao;
import com.my3o.backend.domain.TownAreaEntity;
import com.my3o.common.searchbean.TownAreaSearchBean;

/**
 * @author Eugene Tuysus 14 июля 2014 г.
 * 
 */
@Repository("townAreaDao")
public class TownAreaDaoImpl extends AbstractGenericDao<TownAreaEntity, String, TownAreaSearchBean> implements
        ITownAreaDao {

    @Override
    protected Criteria getExampleCriteria(final TownAreaSearchBean searchBean) {

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

            if (StringUtils.isNotEmpty(searchBean.getTownId())) {
                criteria.add(Restrictions.eq("town.id", searchBean.getTownId()));
            }

        }

        return criteria;
    }
}
