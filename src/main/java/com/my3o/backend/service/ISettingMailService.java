package com.my3o.backend.service;

import java.util.List;

import com.my3o.backend.dao.ISettingMailDao;
import com.my3o.backend.domain.SettingMailEntity;
import com.my3o.common.dto.SettingMailDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.SettingMailSearchBean;

/**
 * @author anton
 * 
 */
public interface ISettingMailService {
    List<SettingMailDto> search(SettingMailSearchBean searchBean) throws BasicServiceException;

    SettingMailDto save(SettingMailDto settingMailDto) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    void setSettingMailDao(ISettingMailDao settingMailDao);

    public SettingMailDto toDto(SettingMailEntity entity);

    public SettingMailEntity toEntity(SettingMailDto dto);

    public ISettingMailDao getSettingMailDao();

}
