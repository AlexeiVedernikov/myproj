package com.my3o.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my3o.backend.dao.ITownAreaDao;
import com.my3o.backend.dao.ITownDao;
import com.my3o.backend.domain.TownAreaEntity;
import com.my3o.backend.service.ITownAreaService;
import com.my3o.backend.service.ITownService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.TownAreaDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.TownAreaSearchBean;

/**
 * @author Eugene Tuysus 14 июля 2014 г.
 * 
 */
@Service
@Transactional
public class TownAreaServiceImpl implements ITownAreaService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ITownAreaDao townAreaDao;

    @Autowired
    private ITownDao townDao;

    @Autowired
    private ITownService townService;

    @Override
    @Transactional(readOnly = false)
    public List<TownAreaDto> search(TownAreaSearchBean searchBean) throws BasicServiceException {
        List<TownAreaDto> result = new ArrayList<TownAreaDto>();

        List<TownAreaEntity> entityList = townAreaDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (TownAreaEntity item : entityList) {
            result.add(toDto(item));
        }

        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public TownAreaDto save(TownAreaDto townAreaDto) throws BasicServiceException {
        TownAreaEntity e = townAreaDao.save(toEntity(townAreaDto));
        return toDto(e);
    }

    // @Override
    // @Transactional(readOnly = false)
    // public void delete(TownDto dto) throws BasicServiceException {
    // TownEntity entity = new TownEntity();
    // entity.setId(dto.getId());
    //
    // townDao.delete(entity);
    // }

    @Override
    public void delete(String pk) throws BasicServiceException {
        townAreaDao.delete(townAreaDao.findById(pk));
    }

    public TownAreaEntity toEntity(TownAreaDto dto) {
        TownAreaEntity entity = new TownAreaEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setTown(townService.getTownDao().findById(dto.getTownDto().getId()));

        return entity;
    }

    public TownAreaDto toDto(TownAreaEntity entity) {
        TownAreaDto dto = new TownAreaDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());

        if (entity.getTown() != null) {
            dto.setTownDto(townService.toDto(entity.getTown()));
        }

        return dto;
    }

    public ITownAreaDao getTownAreaDao() {
        return townAreaDao;
    }

    @Override
    public void setTownAreaDao(ITownAreaDao townAreaDao) {
        this.townAreaDao = townAreaDao;
    }

}
