package com.my3o.backend.service;

import java.util.List;

import com.my3o.backend.dao.ITownAreaDao;
import com.my3o.backend.domain.TownAreaEntity;
import com.my3o.common.dto.TownAreaDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.TownAreaSearchBean;

/**
 * @author Eugene Tuysus 14 июля 2014 г.
 * 
 */
public interface ITownAreaService {
    TownAreaDto save(TownAreaDto townAreaDto) throws BasicServiceException;

    List<TownAreaDto> search(TownAreaSearchBean searchBean) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    public TownAreaDto toDto(TownAreaEntity entity);

    public TownAreaEntity toEntity(TownAreaDto dto);

    public ITownAreaDao getTownAreaDao();

    public void setTownAreaDao(ITownAreaDao townAreaDao);

}
