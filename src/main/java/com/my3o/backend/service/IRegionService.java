package com.my3o.backend.service;

import com.my3o.backend.dao.IRegionDao;
import com.my3o.backend.domain.RegionEntity;
import com.my3o.common.dto.RegionDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.RegionSearchBean;

import java.util.List;

/**
 * Created by chudoshilkin on 10.07.14.
 */
public interface IRegionService {
    List<RegionDto> search(RegionSearchBean searchBean) throws BasicServiceException;
    RegionDto save(RegionDto regionDto) throws BasicServiceException;
    void delete(String pk) throws BasicServiceException;
    public RegionEntity toEntity(RegionDto item);
    public RegionDto toDto(RegionEntity item);
    public IRegionDao getRegionDao();
    public void setRegionDao(IRegionDao regionDao);
}
