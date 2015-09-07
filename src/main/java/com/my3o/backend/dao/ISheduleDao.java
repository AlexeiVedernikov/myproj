package com.my3o.backend.dao;

import com.my3o.backend.domain.NotificationEntity;
import com.my3o.backend.domain.SheduleEntity;
import com.my3o.common.searchbean.NotificationSearchBean;
import com.my3o.common.searchbean.SheduleSearchBean;

public interface ISheduleDao extends IGenericDao<SheduleEntity, String, SheduleSearchBean> {
}
