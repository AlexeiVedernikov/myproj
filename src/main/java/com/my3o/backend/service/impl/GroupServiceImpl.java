package com.my3o.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my3o.backend.dao.IGroupDao;
import com.my3o.backend.domain.GroupEntity;
import com.my3o.backend.service.IGroupService;
import com.my3o.backend.service.IOrganizationService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.GroupDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.GroupSearchBean;

/**
 * @author Eugene Tuysus 21 июля 2014 г.
 * 
 */
@Service
@Transactional
public class GroupServiceImpl implements IGroupService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IGroupDao groupDao;

    // @Autowired
    // private IOrganizationDao organizationDao;

    @Autowired
    private IOrganizationService organizationService;

    @Override
    @Transactional(readOnly = true)
    public List<GroupDto> search(GroupSearchBean searchBean) throws BasicServiceException {
        List<GroupDto> result = new ArrayList<GroupDto>();

        List<GroupEntity> entityList = groupDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (GroupEntity item : entityList) {
            result.add(toDto(item));
        }
        return result;
    }

    @Override
    public GroupDto save(GroupDto groupDto) throws BasicServiceException {
        GroupEntity e = toEntity(groupDto);
        e = groupDao.save(e);
        return toDto(e);

    }

    @Override
    public void delete(String pk) throws BasicServiceException {
        groupDao.delete(groupDao.findById(pk));
    }

    public GroupEntity toEntity(GroupDto dto) {
        GroupEntity entity = new GroupEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setNumber(dto.getNumber());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setLastUpdateTime(dto.getLastUpdateTime());
        entity.setCreateByUserId(dto.getCreateByUserId());
        entity.setCreateTime(dto.getCreateTime());
        entity.setUpdateByUserId(dto.getUpdateByUserId());
        entity.setOrganization(organizationService.getOrganizationDao().findById(dto.getOrganizationDto().getId()));
        return entity;
    }

    public GroupDto toDto(GroupEntity entity) {
        GroupDto entytyDto = new GroupDto();

        entytyDto.setId(entity.getId());
        entytyDto.setName(entity.getName());
        entytyDto.setNumber(entity.getNumber());

        if (entity.getOrganization() != null) {
            entytyDto.setOrganizationDto(organizationService.toDto(entity.getOrganization()));
        }

        entytyDto.setDescription(entity.getDescription());
        entytyDto.setStatus(entity.getStatus());
        entytyDto.setLastUpdateTime(entity.getLastUpdateTime());
        entytyDto.setCreateByUserId(entity.getCreateByUserId());
        entytyDto.setCreateTime(entity.getCreateTime());
        entytyDto.setUpdateByUserId(entity.getCreateByUserId());
        return entytyDto;
    }

    public IGroupDao getGroupDao() {
        return groupDao;
    }

    @Override
    public void setGroupDao(IGroupDao groupDao) {
    }

}
