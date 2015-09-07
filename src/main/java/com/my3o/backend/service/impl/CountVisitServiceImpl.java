package com.my3o.backend.service.impl;

import com.my3o.backend.dao.ICountVisitDao;
import com.my3o.backend.domain.CountVisitEntity;
import com.my3o.backend.service.ICountVisitService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.CountVisitDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.CountVisitSearchBean;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CountVisitServiceImpl implements ICountVisitService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICountVisitDao countVisitDao;

    @Override
    @Transactional(readOnly = true)
    public List<CountVisitDto> search(CountVisitSearchBean searchBean) throws BasicServiceException {
        List<CountVisitDto> result = new ArrayList<CountVisitDto>();

        List<CountVisitEntity> entityList = countVisitDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (CountVisitEntity item : entityList) {
            result.add(toDto(item));
        }
        return result;
    }

    @Override
    public CountVisitDto save(CountVisitDto countVisitDto) throws BasicServiceException {
        // countryDao.save(toEntity(countryDto));
        CountVisitEntity e = countVisitDao.save(toEntity(countVisitDto));
        return toDto(e);

    }

    @Override
    public void delete(String pk) throws BasicServiceException {
        countVisitDao.delete(countVisitDao.findById(pk));
    }

    public CountVisitEntity toEntity(CountVisitDto countVisitDto) {
        CountVisitEntity entity = new CountVisitEntity();

        entity.setId(countVisitDto.getId());
        entity.setName(countVisitDto.getName());
        entity.setDescription(countVisitDto.getDescription());
        entity.setStatus(countVisitDto.getStatus());
        return entity;
    }

    public CountVisitDto toDto(CountVisitEntity countVisitEntity) {
        CountVisitDto countVisitDto = new CountVisitDto();

        countVisitDto.setId(countVisitEntity.getId());
        countVisitDto.setName(countVisitEntity.getName());
        countVisitDto.setDescription(countVisitEntity.getDescription());
        countVisitDto.setStatus(countVisitEntity.getStatus());
        return countVisitDto;
    }



    public ICountVisitDao getCountVisitDao() {
        return countVisitDao;
    }

    public void setCountVisitDao(ICountVisitDao countVisitDao) {
        this.countVisitDao = countVisitDao;
    }
}
