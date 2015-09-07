package com.my3o.backend.dao.impl;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.INotificationDao;
import com.my3o.backend.dao.ISheduleDao;
import com.my3o.backend.domain.NotificationEntity;
import com.my3o.backend.domain.SheduleEntity;
import com.my3o.common.searchbean.NotificationSearchBean;
import com.my3o.common.searchbean.SheduleSearchBean;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by chudoshilkin
 * 
 */
@Repository("sheduleDao")
public class SheduleDaoImpl extends AbstractGenericDao<SheduleEntity, String, SheduleSearchBean>
        implements ISheduleDao {

    @Override
    protected Criteria getExampleCriteria(final SheduleSearchBean searchBean) {
        final Criteria criteria = getCriteria();
        if (StringUtils.isNotBlank(searchBean.getKey())) {
            criteria.add(Restrictions.eq(getPKfieldName(), searchBean.getKey()));
        } else {

            if (StringUtils.isNotEmpty(searchBean.getYear())) {
                criteria.add(Restrictions.eq("year", searchBean.getYear()));
            }

            if (StringUtils.isNotEmpty(searchBean.getCabinet())) {
                criteria.add(Restrictions.eq("cabinet", searchBean.getCabinet()));
            }

            if (StringUtils.isNotEmpty(searchBean.getFromTimes())) {
                criteria.add(Restrictions.eq("fromTimes", searchBean.getFromTimes()));
            }

            if (StringUtils.isNotEmpty(searchBean.getToTimes())) {
                criteria.add(Restrictions.eq("toTimes", searchBean.getToTimes()));
            }

            if (StringUtils.isNotEmpty(searchBean.getDisciplineId())) {
                criteria.add(Restrictions.eq("discipline.id", searchBean.getDisciplineId()));
            }

            if (StringUtils.isNotEmpty(searchBean.getUserId())) {
                criteria.add(Restrictions.eq("user.id", searchBean.getUserId()));
            }

            if (StringUtils.isNotEmpty(searchBean.getGroupId())) {
                criteria.add(Restrictions.eq("group.id", searchBean.getGroupId()));
            }

            if (searchBean.getStatus() != null) {
                criteria.add(Restrictions.eq("status", searchBean.getStatus()));
            }
        }

        return criteria;
    }
}
