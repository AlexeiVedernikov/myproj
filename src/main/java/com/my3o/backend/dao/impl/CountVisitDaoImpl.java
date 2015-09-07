package com.my3o.backend.dao.impl;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.ICountVisitDao;
import com.my3o.backend.domain.CountVisitEntity;
import com.my3o.common.searchbean.CountVisitSearchBean;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("countVisitDao")
public class CountVisitDaoImpl extends AbstractGenericDao<CountVisitEntity, String, CountVisitSearchBean> implements ICountVisitDao {

    @Override
    protected Criteria getExampleCriteria(final CountVisitSearchBean searchBean) {

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
