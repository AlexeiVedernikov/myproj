package com.my3o.backend.service.impl;

import com.my3o.backend.dao.IJournalDao;
import com.my3o.backend.dao.ISheduleDao;
import com.my3o.backend.domain.JournalEntity;
import com.my3o.backend.domain.SheduleEntity;
import com.my3o.backend.service.*;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.constant.MarkType;
import com.my3o.common.constant.Status;
import com.my3o.common.constant.TaskType;
import com.my3o.common.dto.*;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.JournalSearchBean;
import com.my3o.common.searchbean.SheduleSearchBean;
import com.my3o.frontend.web.model.JournalWebModel;
import com.my3o.frontend.web.model.SheduleWebModel;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chudoshilkin
 */
@Service
@Transactional
public class JournalServiceImpl implements IJournalService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IJournalDao journalDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISheduleService sheduleService;


    @Override
    @Transactional(readOnly = true)
    public List<JournalDto> search(JournalSearchBean searchBean) throws BasicServiceException {
        List<JournalDto> result = new ArrayList<JournalDto>();

        List<JournalEntity> entityList = journalDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (JournalEntity item : entityList) {
            result.add(toDto(item));
        }

        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public JournalDto save(JournalDto journalDto) throws BasicServiceException {
        JournalEntity e = journalDao.save(toEntity(journalDto));
        return toDto(e);
    }

    @Override
    public void delete(String pk) throws BasicServiceException {
        journalDao.delete(journalDao.findById(pk));
//        JournalEntity journalEntity = journalDao.findById(pk);
//        journalEntity.setStatus(Status.Deleted);
//        journalDao.save(journalEntity);
    }

    public JournalEntity toEntity(JournalDto dto) {
        JournalEntity entity = new JournalEntity();

        entity.setId(dto.getId());
        entity.setShedule(sheduleService.getSheduleDao().findById(dto.getSheduleDto().getId()));
        entity.setUser(userService.getUserDao().findById(dto.getUserDto().getId()));
        entity.setDate(dto.getDate());
        entity.setMark(dto.getMark());
        entity.setMarkType(dto.getMarkType());
        entity.setTaskType(dto.getTaskType());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());

        return entity;
    }

    public JournalDto toDto(JournalEntity entity) {
        JournalDto dto = new JournalDto();

        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setMark(entity.getMark());
        dto.setMarkType(entity.getMarkType());
        dto.setTaskType(entity.getTaskType());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());

        if (entity.getUser() != null) {
            dto.setUserDto(new UserDto(entity.getUser().getId()));
        }
        if (entity.getShedule() != null) {
            dto.setSheduleDto(new SheduleDto(entity.getShedule().getId()));
        }

        return dto;
    }

    @Transactional(readOnly = false)
    public JournalDto addAndEdit(JournalWebModel journalWebModel) throws BasicServiceException {
        JournalDto journalDto = new JournalDto();

        if ("-1".equals(journalWebModel.getId())) {
            journalDto.setId(null);
        } else {
            journalDto.setId(journalWebModel.getId());
        }

        journalDto.setDate(journalWebModel.getDate());
        journalDto.setMark(journalWebModel.getMark());
        journalDto.setMarkType(journalWebModel.getMarkType());
        journalDto.setTaskType(TaskType.standart);
        journalDto.setDescription("");
        journalDto.setStatus(Status.Active);

        journalDto.setSheduleDto(new SheduleDto(journalWebModel.getSheduleId()));
        journalDto.setUserDto(new UserDto(journalWebModel.getUserId()));

        journalDto = save(journalDto);

        return journalDto;
    }

    public IJournalDao getJournalDao() {
        return journalDao;
    }

    @Override
    public void setJournalDao(IJournalDao journalDao) {
        this.journalDao = journalDao;
    }

}
