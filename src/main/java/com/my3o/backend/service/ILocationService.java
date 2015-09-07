package com.my3o.backend.service;

import java.util.List;

import com.my3o.backend.dao.ILocationDao;
import com.my3o.backend.domain.LocationEntity;
import com.my3o.common.dto.LocationDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.LocationSearchBean;

public interface ILocationService {

    void delete(String pk) throws BasicServiceException;

    List<LocationDto> search(LocationSearchBean searchBean) throws BasicServiceException;

    LocationDto save(LocationDto townDto) throws BasicServiceException;

    public LocationEntity toEntity(LocationDto dto);

    public LocationDto toDto(LocationEntity entity);

    public ILocationDao getLocationDao();

    public void setLocationDao(ILocationDao locationDao);

}
