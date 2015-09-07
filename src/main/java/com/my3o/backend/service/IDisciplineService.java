package com.my3o.backend.service;

import com.my3o.backend.dao.ICountryDao;
import com.my3o.backend.dao.IDisciplineDao;
import com.my3o.backend.dao.IUserDao;
import com.my3o.backend.domain.CountryEntity;
import com.my3o.backend.domain.DisciplineEntity;
import com.my3o.backend.domain.UserEntity;
import com.my3o.common.dto.CountryDto;
import com.my3o.common.dto.DisciplineDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.CountrySearchBean;
import com.my3o.common.searchbean.DisciplineSearchBean;
import com.my3o.common.searchbean.UserSearchBean;
import com.my3o.frontend.web.model.UserWebModel;

import java.util.List;

/**
 * Created by: andrew
 */
public interface IDisciplineService {
    DisciplineDto getDiscipline() throws BasicServiceException;

    List<DisciplineDto> search(DisciplineSearchBean searchBean) throws BasicServiceException;

    DisciplineDto save(DisciplineDto disciplineDto) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    public DisciplineEntity toEntity(DisciplineDto disciplineDto);

    public DisciplineDto toDto(DisciplineEntity disciplineEntity);

    public IDisciplineDao getDisciplineDao();

    public void setDisciplineDao(IDisciplineDao disciplineDao);
}
