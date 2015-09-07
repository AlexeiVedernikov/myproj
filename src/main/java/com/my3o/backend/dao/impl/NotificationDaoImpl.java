package com.my3o.backend.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.INotificationDao;
import com.my3o.backend.domain.NotificationEntity;
import com.my3o.common.searchbean.NotificationSearchBean;

/**
 * @author anton
 * 
 */
@Repository("notificationDao")
public class NotificationDaoImpl extends AbstractGenericDao<NotificationEntity, String, NotificationSearchBean>
        implements INotificationDao {

    @Override
    protected Criteria getExampleCriteria(final NotificationSearchBean searchBean) {
        final Criteria criteria = getCriteria();
        if (StringUtils.isNotBlank(searchBean.getKey())) {
            criteria.add(Restrictions.eq(getPKfieldName(), searchBean.getKey()));
        } else {

            if (StringUtils.isNotEmpty(searchBean.getName())) {
                criteria.add(Restrictions.eq("name", searchBean.getName()));
            }

            if (StringUtils.isNotEmpty(searchBean.getSettingMailId())) {
                criteria.add(Restrictions.eq("settingMail.id", searchBean.getSettingMailId()));
            }

            if (StringUtils.isNotEmpty(searchBean.getUserId())) {
                criteria.add(Restrictions.eq("user.id", searchBean.getUserId()));
            }

            if (StringUtils.isNotEmpty(searchBean.getGroupId())) {
                criteria.add(Restrictions.eq("group.id", searchBean.getGroupId()));
            }

            if (StringUtils.isNotEmpty(searchBean.getOrganizationId())) {
                criteria.add(Restrictions.eq("organization.id", searchBean.getOrganizationId()));
            }

            if (searchBean.getUserType() != null) {
                criteria.add(Restrictions.eq("userType", searchBean.getUserType()));
            }
        }

        return criteria;
    }
}
