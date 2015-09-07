package com.my3o.backend.service;

import java.util.List;

import com.my3o.backend.dao.INotificationDao;
import com.my3o.backend.domain.NotificationEntity;
import com.my3o.common.dto.NotificationDto;
import com.my3o.common.dto.SettingMailDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.NotificationSearchBean;

/**
 * @author anton
 * 
 */
public interface INotificationService {
    List<NotificationDto> search(NotificationSearchBean searchBean) throws BasicServiceException;

    NotificationDto save(NotificationDto notificationDto) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    void setNotificationDao(INotificationDao notificationDao);

    void sendMessage(SettingMailDto smd, UserDto ud, NotificationDto nDto, UserDto childrenDto);

    public void sendSms(UserDto ud, NotificationDto nDto, UserDto childrenDto);

    public NotificationDto toDto(NotificationEntity entity);

    public NotificationEntity toEntity(NotificationDto dto);

    public INotificationDao getNotificationDao();

}
