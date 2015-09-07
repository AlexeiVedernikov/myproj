package com.my3o.backend.dao.impl;

import com.my3o.backend.dao.AbstractGenericDao;
import com.my3o.backend.dao.IUserDao;
import com.my3o.backend.domain.UserEntity;
import com.my3o.common.constant.Status;
import com.my3o.common.searchbean.BaseUserSearchBean;
import com.my3o.common.searchbean.UserSearchBean;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

/**
 * Created by: andrew
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractGenericDao<UserEntity, String, BaseUserSearchBean> implements IUserDao {

    @Override
    protected Criteria getExampleCriteria(final BaseUserSearchBean searchBean) {

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

            if (searchBean instanceof UserSearchBean) {
                UserSearchBean userSearchBean = (UserSearchBean) searchBean;
                criteria.add(Restrictions.eq("status", Status.Active));
//                if (userSearchBean.getStatus() != null) {
//                    criteria.add(Restrictions.eq("status", userSearchBean.getStatus()));
//                }
                if (StringUtils.isNotEmpty(userSearchBean.getName())) {
                    criteria.add(Restrictions.like("name", userSearchBean.getName(), MatchMode.ANYWHERE));
                }

                if (StringUtils.isNotEmpty(userSearchBean.getEmail())) {
                    criteria.add(Restrictions.eq("email", userSearchBean.getEmail()));
                }
                if (((UserSearchBean) searchBean).getIsFake() != null) {
                    criteria.add(Restrictions.eq("isFake", userSearchBean.getIsFake()));
                }
//                if (userSearchBean.getOrganizationList() != null) {
//                    criteria.createAlias("organizationEntitySet","ou", JoinType.LEFT_OUTER_JOIN);
//                    criteria.add(Restrictions.in("ou.id", userSearchBean.getOrganizationList()));
//                }
                if (StringUtils.isNotEmpty(userSearchBean.getOrganizationId())) {
                    criteria.createAlias("organizationEntitySet","ou", JoinType.LEFT_OUTER_JOIN);
                    criteria.add(Restrictions.eq("ou.id", userSearchBean.getOrganizationId()));
                }
                if (StringUtils.isNotEmpty(userSearchBean.getGroupId())) {
                    criteria.createAlias("groupEntitySet", "gu", JoinType.LEFT_OUTER_JOIN);
                    criteria.add(Restrictions.eq("gu.id", userSearchBean.getGroupId()));
                }
                if (StringUtils.isNotEmpty(userSearchBean.getChildrenId())) {
                    criteria.createAlias("childrenSet", "ch", JoinType.LEFT_OUTER_JOIN);
                    criteria.add(Restrictions.eq("ch.id", userSearchBean.getChildrenId()));
                }

            }

        }
        criteria.addOrder(Order.asc("name"));
        return criteria;
    }
}
