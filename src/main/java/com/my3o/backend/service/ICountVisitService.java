package com.my3o.backend.service;

import com.my3o.backend.dao.ICountVisitDao;
import com.my3o.backend.domain.CountVisitEntity;
import com.my3o.common.dto.CountVisitDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.CountVisitSearchBean;

import java.util.List;

public interface ICountVisitService {
    List<CountVisitDto> search(CountVisitSearchBean searchBean) throws BasicServiceException;

    CountVisitDto save(CountVisitDto countVisitDto) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    public CountVisitEntity toEntity(CountVisitDto countVisitDto);

    public CountVisitDto toDto(CountVisitEntity countVisitEntity);

    public ICountVisitDao getCountVisitDao();

    public void setCountVisitDao(ICountVisitDao countVisitDao);
}
