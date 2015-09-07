package com.my3o.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my3o.backend.dao.ICountryDao;
import com.my3o.backend.domain.CountryEntity;
import com.my3o.backend.service.ICountryService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.CountryDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.CountrySearchBean;

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
@Service
@Transactional
public class CountryServiceImpl implements ICountryService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICountryDao countryDao;

    @Override
    @Transactional(readOnly = true)
    public List<CountryDto> search(CountrySearchBean searchBean) throws BasicServiceException {
        List<CountryDto> result = new ArrayList<CountryDto>();

        List<CountryEntity> entityList = countryDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (CountryEntity item : entityList) {
            result.add(toDto(item));
        }
        return result;
    }

    @Override
    public CountryDto save(CountryDto countryDto) throws BasicServiceException {
        // countryDao.save(toEntity(countryDto));
        CountryEntity e = countryDao.save(toEntity(countryDto));
        return toDto(e);

    }

    @Override
    public void delete(String pk) throws BasicServiceException {
        countryDao.delete(countryDao.findById(pk));
    }

    public CountryEntity toEntity(CountryDto countryDto) {
        CountryEntity entity = new CountryEntity();

        entity.setId(countryDto.getId());
        entity.setName(countryDto.getName());
        entity.setDescription(countryDto.getDescription());
        entity.setStatus(countryDto.getStatus());
        return entity;
    }

    public CountryDto toDto(CountryEntity countryEntity) {
        CountryDto entytyDto = new CountryDto();

        entytyDto.setId(countryEntity.getId());
        entytyDto.setName(countryEntity.getName());
        entytyDto.setDescription(countryEntity.getDescription());
        entytyDto.setStatus(countryEntity.getStatus());
        return entytyDto;
    }

    @Override
    public CountryDto getCountry() throws BasicServiceException {
        // TODO Auto-generated method stub
        return null;
    }

    public ICountryDao getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(ICountryDao countryDao) {
        this.countryDao = countryDao;
    }
}
