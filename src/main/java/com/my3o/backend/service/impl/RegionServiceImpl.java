package com.my3o.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my3o.backend.dao.IRegionDao;
import com.my3o.backend.domain.RegionEntity;
import com.my3o.backend.service.ICountryService;
import com.my3o.backend.service.IRegionService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.RegionDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.RegionSearchBean;

/**
 * Created by chudoshilkin on 10.07.14.
 */

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRegionDao regionDao;

    @Autowired
    private ICountryService countryService;

    @Override
    @Transactional(readOnly = true)
    public List<RegionDto> search(RegionSearchBean searchBean) throws BasicServiceException {
        List<RegionDto> result = new ArrayList<RegionDto>();

        List<RegionEntity> entityList = regionDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (RegionEntity item : entityList) {
            result.add(toDto(item));
        }

        return result;
    }

    @Override
    @Transactional
    public RegionDto save(RegionDto regionDto) throws BasicServiceException {
        RegionEntity e = regionDao.save(toEntity(regionDto));
        return toDto(e);
    }

    @Override
    public void delete(String pk) throws BasicServiceException {
        regionDao.delete(regionDao.findById(pk));
    }

    public RegionEntity toEntity(RegionDto item) {
        RegionEntity entity = new RegionEntity();
        entity.setId(item.getId());
        entity.setName(item.getName());
        entity.setDescription(item.getDescription());
        entity.setStatus(item.getStatus());
        entity.setCountry(countryService.getCountryDao().findById(item.getCountryDto().getId()));

        return entity;
    }

    public RegionDto toDto(RegionEntity entity) {
        RegionDto e = new RegionDto();
        e.setId(entity.getId());
        e.setName(entity.getName());
        e.setDescription(entity.getDescription());
        e.setStatus(entity.getStatus());

        if (entity.getCountry() != null) {
            e.setCountryDto(countryService.toDto(entity.getCountry()));
        }

        return e;
    }

    public IRegionDao getRegionDao() {
        return regionDao;
    }

    public void setRegionDao(IRegionDao regionDao) {
        this.regionDao = regionDao;
    }
}