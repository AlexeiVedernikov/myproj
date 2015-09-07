package com.my3o.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my3o.backend.dao.ISettingMailDao;
import com.my3o.backend.domain.SettingMailEntity;
import com.my3o.backend.service.IOrganizationService;
import com.my3o.backend.service.ISettingMailService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.SettingMailDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.SettingMailSearchBean;

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
@Service
@Transactional
public class SettingMailServiceImpl implements ISettingMailService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private ISettingMailDao settingMailDao;

    @Override
    @Transactional(readOnly = true)
    public List<SettingMailDto> search(SettingMailSearchBean searchBean) throws BasicServiceException {
        List<SettingMailDto> result = new ArrayList<SettingMailDto>();

        List<SettingMailEntity> entityList = settingMailDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (SettingMailEntity item : entityList) {
            result.add(toDto(item));
        }

        return result;
    }

    @Override
    // @Transactional(readOnly = false)
    public SettingMailDto save(SettingMailDto settingMailDto) throws BasicServiceException {
        // townDao.save(toEntity(townDto));
        SettingMailEntity e = settingMailDao.save(toEntity(settingMailDto));
        return toDto(e);
    }

    @Override
    public void delete(String pk) throws BasicServiceException {
        settingMailDao.delete(settingMailDao.findById(pk));
    }

    public SettingMailEntity toEntity(SettingMailDto dto) {
        SettingMailEntity entity = new SettingMailEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setHost(dto.getHost());
        entity.setPort(dto.getPort());
        entity.setUserName(dto.getUserName());
        entity.setUserPassword(dto.getUserPassword());
        entity.setDefaultSender(dto.getDefaultSender());
        entity.setDefaultSubjectPrefix(dto.getDefaultSubjectPrefix());

        entity.setOrganization(organizationService.getOrganizationDao().findById(dto.getOrganizationDto().getId()));

        return entity;
    }

    public SettingMailDto toDto(SettingMailEntity entity) {
        SettingMailDto dto = new SettingMailDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setHost(entity.getHost());
        dto.setPort(entity.getPort());
        dto.setUserName(entity.getUserName());
        dto.setUserPassword(entity.getUserPassword());
        dto.setDefaultSender(entity.getDefaultSender());
        dto.setDefaultSubjectPrefix(entity.getDefaultSubjectPrefix());

        if (entity.getOrganization() != null) {
            dto.setOrganizationDto(organizationService.toDto(entity.getOrganization()));
        }

        return dto;
    }

    public ISettingMailDao getSettingMailDao() {
        return settingMailDao;
    }

    @Override
    public void setSettingMailDao(ISettingMailDao settingMailDao) {
        this.settingMailDao = settingMailDao;
    }

}
