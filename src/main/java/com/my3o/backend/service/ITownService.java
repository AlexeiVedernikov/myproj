package com.my3o.backend.service;

import java.util.List;

import com.my3o.backend.dao.ITownDao;
import com.my3o.backend.domain.TownEntity;
import com.my3o.common.dto.TownDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.TownSearchBean;

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
public interface ITownService {
    List<TownDto> search(TownSearchBean searchBean) throws BasicServiceException;

    TownDto save(TownDto townDto) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    public TownDto toDto(TownEntity entity);

    public TownEntity toEntity(TownDto dto);

    public ITownDao getTownDao();

    public void setTownDao(ITownDao townDao);

}
