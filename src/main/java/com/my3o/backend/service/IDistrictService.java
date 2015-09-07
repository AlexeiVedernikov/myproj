package com.my3o.backend.service;

import com.my3o.backend.dao.IDistrictDao;
import com.my3o.backend.domain.DistrictEntity;
import com.my3o.common.dto.DistrictDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.DistrictSearchBean;

import java.util.List;

/**
 * Created by chudoshilkin on 11.07.14.
 */
public interface IDistrictService {
    List<DistrictDto> search(DistrictSearchBean searchBean) throws BasicServiceException;
    DistrictDto save(DistrictDto regionDto) throws BasicServiceException;
    void delete(String pk) throws BasicServiceException;
    public DistrictEntity toEntity(DistrictDto item);
    public DistrictDto toDto(DistrictEntity item);
    public IDistrictDao getDistrictDao();
    public void setDistrictDao(IDistrictDao districtDao);
}
