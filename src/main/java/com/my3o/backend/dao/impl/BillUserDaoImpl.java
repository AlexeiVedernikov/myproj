package com.my3o.backend.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.IBillUserDao;
import com.my3o.backend.domain.BillUserEntity;
import com.my3o.common.searchbean.BillUserSearchBean;

/**
 * @author anton
 * 
 */
@Repository("billUserDao")
public class BillUserDaoImpl extends AbstractGenericDao<BillUserEntity, String, BillUserSearchBean> implements
        IBillUserDao {

    @Override
    protected Criteria getExampleCriteria(final BillUserSearchBean searchBean) {

        final Criteria criteria = getCriteria();
        if (searchBean.hasMultipleKeys()) {
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

            if (searchBean.getQiwiStatus() != null) {
                criteria.add(Restrictions.eq("qiwiStatus", searchBean.getQiwiStatus()));
            }

            if (StringUtils.isNotEmpty(searchBean.getUserId())) {
                criteria.add(Restrictions.eq("user.id", searchBean.getUserId()));
            }

        }

        return criteria;
    }
}
