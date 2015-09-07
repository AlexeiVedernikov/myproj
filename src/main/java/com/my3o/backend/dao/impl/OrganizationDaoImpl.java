package com.my3o.backend.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.IOrganizationDao;
import com.my3o.backend.domain.OrganizationEntity;
import com.my3o.common.searchbean.OrganizationSearchBean;

@Repository("organizationDao")
public class OrganizationDaoImpl extends AbstractGenericDao<OrganizationEntity, String, OrganizationSearchBean>
        implements IOrganizationDao {
    @Override
    protected Criteria getExampleCriteria(final OrganizationSearchBean searchBean) {

        final Criteria criteria = getCriteria();

        if (searchBean.hasMultipleKeys()) {
            criteria.add(Restrictions.in(getPKfieldName(), searchBean.getKeys()));
        } else if (StringUtils.isNotBlank(searchBean.getKey())) {
            criteria.add(Restrictions.eq(getPKfieldName(), searchBean.getKey()));
        } else {

            if (StringUtils.isNotEmpty(searchBean.getOrganizationName())) {
                criteria.add(Restrictions.eq("name", searchBean.getOrganizationName()));
            }

            if (searchBean.getStatus() != null) {
                criteria.add(Restrictions.eq("status", searchBean.getStatus()));
            }

        }
        criteria.addOrder(Order.asc("name"));
        return criteria;
    }
}
