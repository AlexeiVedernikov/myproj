package com.my3o.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my3o.backend.dao.IOrganizationDao;
import com.my3o.backend.domain.OrganizationEntity;
import com.my3o.backend.service.ILocationService;
import com.my3o.backend.service.IOrganizationService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.OrganizationDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.OrganizationSearchBean;

@Service
@Transactional
public class OrganizationServiceImpl implements IOrganizationService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IOrganizationDao organizationDao;

    @Autowired
    private ILocationService locationService;

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationDto> search(OrganizationSearchBean searchBean) throws BasicServiceException {
        List<OrganizationDto> result = new ArrayList<OrganizationDto>();

        List<OrganizationEntity> entityList = organizationDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (OrganizationEntity item : entityList) {
            result.add(toDto(item));
        }

        return result;
    }

    @Override
    public OrganizationDto save(OrganizationDto organizationDto) throws BasicServiceException {
        OrganizationEntity e = organizationDao.save(toEntity(organizationDto));
        return toDto(e);
    }

    @Override
    public void delete(String pk) throws BasicServiceException {
        organizationDao.delete(organizationDao.findById(pk));
    }

    public OrganizationEntity toEntity(OrganizationDto dto) throws BasicServiceException {

        OrganizationEntity entity = new OrganizationEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setLocation(locationService.getLocationDao().findById(dto.getLocationDto().getId()));

        return entity;
    }

    public OrganizationDto toDto(OrganizationEntity entity) {
        OrganizationDto dto = new OrganizationDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());

        if (entity.getLocation() != null) {
            dto.setLocationDto(locationService.toDto(entity.getLocation()));
        }
        return dto;
    }

    public IOrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    @Override
    public void setOrganizationDao(IOrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

}
