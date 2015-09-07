package com.my3o.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my3o.backend.dao.IDistrictDao;
import com.my3o.backend.dao.IRegionDao;
import com.my3o.backend.dao.ITownDao;
import com.my3o.backend.domain.TownEntity;
import com.my3o.backend.service.IDistrictService;
import com.my3o.backend.service.IRegionService;
import com.my3o.backend.service.ITownService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.TownDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.TownSearchBean;

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
@Service
@Transactional
public class TownServiceImpl implements ITownService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ITownDao townDao;

    @Autowired
    private IRegionDao regionDao;

    @Autowired
    private IDistrictDao districtDao;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IDistrictService districtService;

    @Override
    @Transactional(readOnly = true)
    public List<TownDto> search(TownSearchBean searchBean) throws BasicServiceException {
        List<TownDto> result = new ArrayList<TownDto>();

        List<TownEntity> entityList = townDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (TownEntity item : entityList) {
            result.add(toDto(item));
        }

        return result;
    }

    @Override
    // @Transactional(readOnly = false)
    public TownDto save(TownDto townDto) throws BasicServiceException {
        // townDao.save(toEntity(townDto));
        TownEntity e = townDao.save(toEntity(townDto));
        return toDto(e);
    }

    @Override
    public void delete(String pk) throws BasicServiceException {
        townDao.delete(townDao.findById(pk));
    }

    public TownEntity toEntity(TownDto dto) {
        TownEntity entity = new TownEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());

        entity.setRegion(regionService.getRegionDao().findById(dto.getRegionDto().getId()));

        entity.setDistrict(districtService.getDistrictDao().findById(dto.getDistrictDto().getId()));

        return entity;
    }

    public TownDto toDto(TownEntity entity) {
        TownDto dto = new TownDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());

        if (entity.getRegion() != null) {
            dto.setRegionDto(regionService.toDto(entity.getRegion()));
        }

        if (entity.getDistrict() != null) {
            dto.setDistrictDto(districtService.toDto(entity.getDistrict()));
        }
        return dto;
    }

    public ITownDao getTownDao() {
        return townDao;
    }

    @Override
    public void setTownDao(ITownDao townDao) {
        this.townDao = townDao;
    }

}
