package com.my3o.backend.service.impl;

import com.my3o.backend.config.IBackendProperties;
import com.my3o.backend.dao.*;
import com.my3o.backend.domain.*;
import com.my3o.backend.service.*;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.constant.QiwiStatus;
import com.my3o.common.constant.Status;
import com.my3o.common.constant.UserType;
import com.my3o.common.dto.*;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.DisciplineSearchBean;
import com.my3o.common.searchbean.GroupSearchBean;
import com.my3o.common.searchbean.OrganizationSearchBean;
import com.my3o.common.searchbean.UserSearchBean;
import com.my3o.frontend.web.model.UserWebModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by: andrew
 */
@Service
@Transactional
public class DisciplineServiceImpl implements IDisciplineService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDisciplineDao disciplineDao;

    @Override
    @Transactional(readOnly = true)
    public List<DisciplineDto> search(DisciplineSearchBean searchBean) throws BasicServiceException {
        List<DisciplineDto> result = new ArrayList<DisciplineDto>();

        List<DisciplineEntity> entityList = disciplineDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (DisciplineEntity disciplineEntity : disciplineDao.getByExample(searchBean)) {
            result.add(toDto(disciplineEntity));
        }

        return result;
    }

    @Override
    public DisciplineDto save(DisciplineDto disciplineDto) throws BasicServiceException {
        // countryDao.save(toEntity(countryDto));y
        DisciplineEntity entity = disciplineDao.save(toEntity(disciplineDto));
        return toDto(entity);

    }

    @Override
    public void delete(String pk) throws BasicServiceException {
        disciplineDao.delete(disciplineDao.findById(pk));
    }

    public DisciplineEntity toEntity(DisciplineDto disciplineDto) {
        DisciplineEntity entity = new DisciplineEntity();

        entity.setId(disciplineDto.getId());
        entity.setName(disciplineDto.getName());
        entity.setDescription(disciplineDto.getDescription());
        entity.setStatus(disciplineDto.getStatus());
        return entity;
    }

    public DisciplineDto toDto(DisciplineEntity disciplineEntity) {
        DisciplineDto disciplineDto = new DisciplineDto();

        disciplineDto.setId(disciplineEntity.getId());
        disciplineDto.setName(disciplineEntity.getName());
        disciplineDto.setDescription(disciplineEntity.getDescription());
        disciplineDto.setStatus(disciplineEntity.getStatus());
        return disciplineDto;
    }

    @Override
    public DisciplineDto getDiscipline() throws BasicServiceException {
        // TODO Auto-generated method stub
        return null;
    }

    public IDisciplineDao getDisciplineDao() {
        return disciplineDao;
    }

    public void setDisciplineDao(IDisciplineDao disciplineDao) {
        this.disciplineDao = disciplineDao;
    }

}
