package com.my3o.backend.service;

import java.util.List;

import com.my3o.backend.dao.IOrganizationDao;
import com.my3o.backend.domain.OrganizationEntity;
import com.my3o.common.dto.OrganizationDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.OrganizationSearchBean;

public interface IOrganizationService {

    void delete(String pk) throws BasicServiceException;

    List<OrganizationDto> search(OrganizationSearchBean searchBean) throws BasicServiceException;

    OrganizationDto save(OrganizationDto OrganizationDto) throws BasicServiceException;

    public OrganizationEntity toEntity(OrganizationDto dto) throws BasicServiceException;

    public OrganizationDto toDto(OrganizationEntity entity);

    public IOrganizationDao getOrganizationDao();

    public void setOrganizationDao(IOrganizationDao organizationDao);

}
