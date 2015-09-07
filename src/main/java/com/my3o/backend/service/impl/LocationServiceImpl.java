package com.my3o.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my3o.backend.dao.ILocationDao;
import com.my3o.backend.domain.LocationEntity;
import com.my3o.backend.service.ICountryService;
import com.my3o.backend.service.IDistrictService;
import com.my3o.backend.service.ILocationService;
import com.my3o.backend.service.IRegionService;
import com.my3o.backend.service.ITownAreaService;
import com.my3o.backend.service.ITownService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.LocationDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.LocationSearchBean;

@Service
@Transactional
public class LocationServiceImpl implements ILocationService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ILocationDao locationDao;

    @Autowired
    private ICountryService countryService;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IDistrictService districtService;

    @Autowired
    private ITownService townService;

    @Autowired
    private ITownAreaService townAreaService;

    @Override
    @Transactional(readOnly = true)
    public List<LocationDto> search(LocationSearchBean searchBean) throws BasicServiceException {
        List<LocationDto> result = new ArrayList<LocationDto>();

        List<LocationEntity> entityList = locationDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (LocationEntity item : entityList) {
            result.add(toDto(item));
        }

        return result;
    }

    @Override
    public LocationDto save(LocationDto locationDto) throws BasicServiceException {
        LocationEntity e = locationDao.save(toEntity(locationDto));
        return toDto(e);
    }

    @Override
    public void delete(String pk) throws BasicServiceException {
        locationDao.delete(locationDao.findById(pk));
    }

    public LocationEntity toEntity(LocationDto dto) {
        LocationEntity entity = new LocationEntity();

        entity.setId(dto.getId());

        entity.setCountry(countryService.getCountryDao().findById(dto.getCountryDto().getId()));
        entity.setRegion(regionService.getRegionDao().findById(dto.getRegionDto().getId()));
        if (dto.getDistrictDto() != null) {
            entity.setDistrict(districtService.getDistrictDao().findById(dto.getDistrictDto().getId()));
        }
        if (dto.getTownDto() != null) {
            entity.setTown(townService.getTownDao().findById(dto.getTownDto().getId()));
        }
        if (dto.getTownAreaDto() != null) {
            entity.setTownArea(townAreaService.getTownAreaDao().findById(dto.getTownAreaDto().getId()));
        }

        return entity;
    }

    public LocationDto toDto(LocationEntity entity) {
        LocationDto dto = new LocationDto();

        dto.setId(entity.getId());

        if (entity.getCountry() != null) {
            dto.setCountryDto(countryService.toDto(entity.getCountry()));
        }

        if (entity.getRegion() != null) {
            dto.setRegionDto(regionService.toDto(entity.getRegion()));
        }

        if (entity.getDistrict() != null) {
            dto.setDistrictDto(districtService.toDto(entity.getDistrict()));
        }

        if (entity.getTown() != null) {
            dto.setTownDto(townService.toDto(entity.getTown()));
        }

        if (entity.getTownArea() != null) {
            dto.setTownAreaDto(townAreaService.toDto(entity.getTownArea()));
        }

        return dto;
    }

    public ILocationDao getLocationDao() {
        return locationDao;
    }

    @Override
    public void setLocationDao(ILocationDao locationDao) {
        this.locationDao = locationDao;
    }
}
