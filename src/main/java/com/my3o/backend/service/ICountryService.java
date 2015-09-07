package com.my3o.backend.service;

import java.util.List;

import com.my3o.backend.dao.ICountryDao;
import com.my3o.backend.domain.CountryEntity;
import com.my3o.common.dto.CountryDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.CountrySearchBean;

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
public interface ICountryService {
    CountryDto getCountry() throws BasicServiceException;

    List<CountryDto> search(CountrySearchBean searchBean) throws BasicServiceException;

    CountryDto save(CountryDto countryDto) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    public CountryEntity toEntity(CountryDto countryDto);

    public CountryDto toDto(CountryEntity countryEntity);

    public ICountryDao getCountryDao();

    public void setCountryDao(ICountryDao countryDao);
}
