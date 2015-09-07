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
import com.my3o.backend.domain.DistrictEntity;
import com.my3o.backend.service.IDistrictService;
import com.my3o.backend.service.IRegionService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.DistrictDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.DistrictSearchBean;

/**
 * Created by chudoshilkin on 11.07.14.
 */

@Service
@Transactional
public class DistrictServiceImpl implements IDistrictService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDistrictDao districtDao;

    @Autowired
    private IRegionService regionService;

    @Override
    @Transactional(readOnly = true)
    public List<DistrictDto> search(DistrictSearchBean searchBean) throws BasicServiceException {
        List<DistrictDto> result = new ArrayList<DistrictDto>();

        List<DistrictEntity> entityList = districtDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (DistrictEntity item : entityList) {
            result.add(toDto(item));
        }

        return result;
    }

    @Override
    @Transactional
    public DistrictDto save(DistrictDto districtDto) throws BasicServiceException {
        DistrictEntity e = districtDao.save(toEntity(districtDto));
        return toDto(e);
    }

    @Override
    public void delete(String pk) throws BasicServiceException {
        districtDao.delete(districtDao.findById(pk));
    }

    public DistrictEntity toEntity(DistrictDto item) {
        DistrictEntity entity = new DistrictEntity();
        entity.setId(item.getId());
        entity.setName(item.getName());
        entity.setDescription(item.getDescription());
        entity.setStatus(item.getStatus());
        entity.setRegion(regionService.getRegionDao().findById(item.getRegionDto().getId()));

        return entity;
    }

    public DistrictDto toDto(DistrictEntity entity) {
        DistrictDto e = new DistrictDto();
        e.setId(entity.getId());
        e.setName(entity.getName());
        e.setDescription(entity.getDescription());
        e.setStatus(entity.getStatus());

        if (entity.getRegion() != null) {
            e.setRegionDto(regionService.toDto(entity.getRegion()));
        }

        return e;
    }

    public IDistrictDao getDistrictDao() {
        return districtDao;
    }

    @Override
    public void setDistrictDao(IDistrictDao districtDao) {
        // TODO Auto-generated method stub
        this.districtDao = districtDao;
    }

}
