package com.my3o.backend.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.ISettingMailDao;
import com.my3o.backend.domain.SettingMailEntity;
import com.my3o.common.searchbean.SettingMailSearchBean;

/**
 * @author anton
 * 
 */
@Repository("settingMailDao")
public class SettingMailDaoImpl extends AbstractGenericDao<SettingMailEntity, String, SettingMailSearchBean> implements
        ISettingMailDao {

    @Override
    protected Criteria getExampleCriteria(final SettingMailSearchBean searchBean) {
        final Criteria criteria = getCriteria();
        if (StringUtils.isNotBlank(searchBean.getKey())) {
            criteria.add(Restrictions.eq(getPKfieldName(), searchBean.getKey()));
        } else {

            if (StringUtils.isNotEmpty(searchBean.getName())) {
                criteria.add(Restrictions.eq("name", searchBean.getName()));
            }

            if (StringUtils.isNotEmpty(searchBean.getUserName())) {
                criteria.add(Restrictions.eq("userName", searchBean.getUserName()));
            }

            if (StringUtils.isNotEmpty(searchBean.getOrganizationId())) {
                criteria.add(Restrictions.eq("organization.id", searchBean.getOrganizationId()));
            }
        }

        return criteria;
    }
}
