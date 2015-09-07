package com.my3o.backend.dao.impl;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.IGroupDao;
import com.my3o.backend.dao.IJournalDao;
import com.my3o.backend.domain.GroupEntity;
import com.my3o.backend.domain.JournalEntity;
import com.my3o.common.searchbean.GroupSearchBean;
import com.my3o.common.searchbean.JournalSearchBean;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author Rina 30/10/14
 * 
 */
@Repository("journalDao")
public class JournalDaoImpl extends AbstractGenericDao<JournalEntity, String, JournalSearchBean> implements IJournalDao {

    @Override
    protected Criteria getExampleCriteria(final JournalSearchBean searchBean) {

        final Criteria criteria = getCriteria();
        if(searchBean.hasMultipleKeys()){
            criteria.add(Restrictions.in(getPKfieldName(), searchBean.getKeys()));
        } else if (StringUtils.isNotBlank(searchBean.getKey())) {
            criteria.add(Restrictions.eq(getPKfieldName(), searchBean.getKey()));
        } else {

            if (searchBean.getSheduleIdList().size() > 0) {
                criteria.add(Restrictions.in("shedule.id", searchBean.getSheduleIdList()));
            }

            if (StringUtils.isNotEmpty(searchBean.getUserId())) {
                criteria.add(Restrictions.eq("user.id", searchBean.getUserId()));
            }

            if (StringUtils.isNotEmpty(searchBean.getMark())) {
                criteria.add(Restrictions.eq("mark", searchBean.getMark()));
            }

            if (searchBean.getMarkType() != null) {
                criteria.add(Restrictions.eq("markType", searchBean.getMarkType()));
            }

            if (searchBean.getTaskType() != null) {
                criteria.add(Restrictions.eq("taskType", searchBean.getMarkType()));
            }

            if (StringUtils.isNotEmpty(searchBean.getDescription())) {
                criteria.add(Restrictions.eq("description", searchBean.getDescription()));
            }

            if (searchBean.getStatus() != null) {
                criteria.add(Restrictions.eq("status", searchBean.getStatus()));
            }

            if (searchBean.getDateList() != null) {
                criteria.add(Restrictions.in("date", searchBean.getDateList()));
            }

            if (StringUtils.isNotEmpty(searchBean.getDate())) {
                criteria.add(Restrictions.like("date", searchBean.getDate(), MatchMode.ANYWHERE));
            }
        }
//        criteria.addOrder(Order.asc("user.id"));
        return criteria;
    }
}
