package com.my3o.backend.dao.impl;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.IDisciplineDao;
import com.my3o.backend.dao.IUserDao;
import com.my3o.backend.domain.DisciplineEntity;
import com.my3o.backend.domain.UserEntity;
import com.my3o.common.searchbean.BaseUserSearchBean;
import com.my3o.common.searchbean.DisciplineSearchBean;
import com.my3o.common.searchbean.UserSearchBean;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

/**
 * Created by: andrew
 */
@Repository("disciplineDao")
public class DisciplineDaoImpl extends AbstractGenericDao<DisciplineEntity, String, DisciplineSearchBean> implements IDisciplineDao {

    @Override
    protected Criteria getExampleCriteria(final DisciplineSearchBean searchBean) {

        final Criteria criteria = getCriteria();
        if(searchBean.hasMultipleKeys()){
            criteria.add(Restrictions.in(getPKfieldName(), searchBean.getKeys()));
        } else if (StringUtils.isNotBlank(searchBean.getKey())) {
            criteria.add(Restrictions.eq(getPKfieldName(), searchBean.getKey()));
        } else {
            if (StringUtils.isNotEmpty(searchBean.getLogin())) {
                criteria.add(Restrictions.eq("login", searchBean.getLogin()));
            }

            if (searchBean.getUserType() != null) {
                criteria.add(Restrictions.eq("userType", searchBean.getUserType()));
            }

            if (StringUtils.isNotEmpty(searchBean.getName())) {
                criteria.add(Restrictions.eq("name", searchBean.getName()));
            }
        }

        return criteria;
    }
}
