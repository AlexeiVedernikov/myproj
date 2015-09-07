package com.my3o.backend.service;

import java.util.List;

import com.my3o.backend.dao.IGroupDao;
import com.my3o.backend.domain.GroupEntity;
import com.my3o.common.dto.GroupDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.GroupSearchBean;

/**
 * @author Eugene Tuysus 21 июля 2014 г.
 * 
 */
public interface IGroupService {
    // GroupDto getGroup() throws BasicServiceException;

    List<GroupDto> search(GroupSearchBean searchBean) throws BasicServiceException;

    GroupDto save(GroupDto groupDto) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    public GroupEntity toEntity(GroupDto groupDto);

    public GroupDto toDto(GroupEntity groupEntity);

    public IGroupDao getGroupDao();

    public void setGroupDao(IGroupDao groupDao);
}
