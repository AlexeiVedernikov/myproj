package com.my3o.backend.dao;

import com.my3o.backend.domain.UserEntity;
import com.my3o.common.searchbean.BaseUserSearchBean;
import com.my3o.common.searchbean.UserSearchBean;

/**
 * Created by: andrew
 */
public interface IUserDao extends IGenericDao<UserEntity, String, BaseUserSearchBean> {

}
