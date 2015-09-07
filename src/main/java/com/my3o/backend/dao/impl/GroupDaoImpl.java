package com.my3o.backend.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.IGroupDao;
import com.my3o.backend.domain.GroupEntity;
import com.my3o.common.searchbean.GroupSearchBean;

/**
 * @author Eugene Tuysus 21 июля 2014 г.
 * 
 */
@Repository("groupDao")
public class GroupDaoImpl extends AbstractGenericDao<GroupEntity, String, GroupSearchBean> implements IGroupDao {

    @Override
    protected Criteria getExampleCriteria(final GroupSearchBean searchBean) {

        final Criteria criteria = getCriteria();
        if (StringUtils.isNotBlank(searchBean.getKey())) {
            criteria.add(Restrictions.eq(getPKfieldName(), searchBean.getKey()));
        } else {

            if (StringUtils.isNotEmpty(searchBean.getName())) {
                criteria.add(Restrictions.eq("name", searchBean.getName()));
            }

            if (StringUtils.isNotEmpty(searchBean.getNumber())) {
                criteria.add(Restrictions.eq("number", searchBean.getNumber()));
            }

            if (StringUtils.isNotEmpty(searchBean.getOrganizationId())) {
                criteria.add(Restrictions.eq("organization.id", searchBean.getOrganizationId()));
            }

        }
//        criteria.addOrder(Order.asc("number"));
        return criteria;
    }
}
