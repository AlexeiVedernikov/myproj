package com.my3o.backend.service.impl;

import com.my3o.backend.dao.*;
import com.my3o.backend.domain.NotificationEntity;
import com.my3o.backend.domain.SheduleEntity;
import com.my3o.backend.service.*;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.constant.Status;
import com.my3o.common.dto.*;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.NotificationSearchBean;
import com.my3o.common.searchbean.SheduleSearchBean;
import com.my3o.frontend.web.model.SheduleWebModel;
import com.my3o.mail.impl.Sender;
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
public class SheduleServiceImpl implements ISheduleService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ISheduleDao sheduleDao;


    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IDisciplineService disciplineService;


    @Override
    @Transactional(readOnly = true)
    public List<SheduleDto> search(SheduleSearchBean searchBean) throws BasicServiceException {
        List<SheduleDto> result = new ArrayList<SheduleDto>();

        List<SheduleEntity> entityList = sheduleDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (SheduleEntity item : entityList) {
            result.add(toDto(item));
        }

        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public SheduleDto save(SheduleDto sheduleDto) throws BasicServiceException {
        SheduleEntity e = sheduleDao.save(toEntity(sheduleDto));
        return toDto(e);
    }

    @Override
    public void delete(String pk) throws BasicServiceException {
//        sheduleDao.delete(sheduleDao.findById(pk));
        SheduleEntity sheduleEntity = sheduleDao.findById(pk);
        sheduleEntity.setStatus(Status.Deleted);
        sheduleDao.save(sheduleEntity);
    }

    public SheduleEntity toEntity(SheduleDto dto) {
        SheduleEntity entity = new SheduleEntity();

        entity.setId(dto.getId());
        entity.setDiscipline(disciplineService.getDisciplineDao().findById(dto.getDisciplineDto().getId()));
        entity.setUser(userService.getUserDao().findById(dto.getUserDto().getId()));
        entity.setGroup(groupService.getGroupDao().findById(dto.getGroupDto().getId()));
        entity.setYear(dto.getYear());
        entity.setCabinet(dto.getCabinet());
        entity.setFromTimes(dto.getFromTimes());
        entity.setToTimes(dto.getToTimes());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());

        return entity;
    }

    public SheduleDto toDto(SheduleEntity entity) {
        SheduleDto dto = new SheduleDto();

        dto.setId(entity.getId());
        dto.setYear(entity.getYear());
        dto.setCabinet(entity.getCabinet());
        dto.setFromTimes(entity.getFromTimes());
        dto.setToTimes(entity.getToTimes());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());

        if (entity.getUser() != null) {
            dto.setUserDto(userService.toDto(entity.getUser()));
        }
        if (entity.getGroup() != null) {
            dto.setGroupDto(groupService.toDto(entity.getGroup()));
        }
        if (entity.getDiscipline() != null) {
            dto.setDisciplineDto(disciplineService.toDto(entity.getDiscipline()));
        }

        return dto;
    }

    @Transactional(readOnly = false)
    public SheduleDto addAndEdit(SheduleWebModel sheduleWebModel)  throws BasicServiceException {
        SheduleDto sheduleDto = new SheduleDto();

        if ("-1".equals(sheduleWebModel.getId())) {
            sheduleDto.setId(null);
        } else {
            sheduleDto.setId(sheduleWebModel.getId());
        }

        sheduleDto.setYear(sheduleWebModel.getYear());
        sheduleDto.setCabinet(sheduleWebModel.getCabinet());
        sheduleDto.setFromTimes(sheduleWebModel.getFromTimes());
        sheduleDto.setToTimes(sheduleWebModel.getToTimes());
        sheduleDto.setDescription(sheduleWebModel.getDescription());
        sheduleDto.setStatus(Status.Active);

        sheduleDto.setDisciplineDto(new DisciplineDto(sheduleWebModel.getDisciplineId()));
        sheduleDto.setUserDto(new UserDto(sheduleWebModel.getUserId()));
        sheduleDto.setGroupDto(new GroupDto(sheduleWebModel.getGroupId()));

        sheduleDto = save(sheduleDto);

        return sheduleDto;
    }

    public ISheduleDao getSheduleDao() {
        return sheduleDao;
    }

    @Override
    public void setSheduleDao(ISheduleDao sheduleDao) {
        this.sheduleDao = sheduleDao;
    }

}
