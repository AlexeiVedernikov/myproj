package com.my3o.backend.service;

import com.my3o.backend.dao.INotificationDao;
import com.my3o.backend.dao.ISheduleDao;
import com.my3o.backend.domain.NotificationEntity;
import com.my3o.backend.domain.SheduleEntity;
import com.my3o.common.dto.NotificationDto;
import com.my3o.common.dto.SettingMailDto;
import com.my3o.common.dto.SheduleDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.NotificationSearchBean;
import com.my3o.common.searchbean.SheduleSearchBean;
import com.my3o.frontend.web.model.SheduleWebModel;

import java.util.List;

/**
 * Created by chudoshilkin
 * 
 */
public interface ISheduleService {
    List<SheduleDto> search(SheduleSearchBean searchBean) throws BasicServiceException;

    SheduleDto save(SheduleDto sheduleDto) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    public SheduleDto toDto(SheduleEntity entity);

    public SheduleEntity toEntity(SheduleDto dto);

    public ISheduleDao getSheduleDao();

    public void setSheduleDao(ISheduleDao sheduleDao);

    public SheduleDto addAndEdit(SheduleWebModel sheduleWebModel) throws BasicServiceException;

}
