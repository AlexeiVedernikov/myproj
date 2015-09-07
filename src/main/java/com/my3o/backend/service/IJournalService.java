package com.my3o.backend.service;

import com.my3o.backend.dao.IJournalDao;
import com.my3o.backend.dao.ISheduleDao;
import com.my3o.backend.domain.JournalEntity;
import com.my3o.backend.domain.SheduleEntity;
import com.my3o.common.dto.JournalDto;
import com.my3o.common.dto.SheduleDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.JournalSearchBean;
import com.my3o.common.searchbean.SheduleSearchBean;
import com.my3o.frontend.web.model.JournalWebModel;
import com.my3o.frontend.web.model.SheduleWebModel;

import java.util.List;

/**
 * Created by chudoshilkin
 * 
 */
public interface IJournalService {
    List<JournalDto> search(JournalSearchBean searchBean) throws BasicServiceException;

    JournalDto save(JournalDto journalDto) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    public JournalDto toDto(JournalEntity entity);

    public JournalEntity toEntity(JournalDto dto);

    public IJournalDao getJournalDao();

    public void setJournalDao(IJournalDao journalDao);

    public JournalDto addAndEdit(JournalWebModel journalWebModel) throws BasicServiceException;

}
