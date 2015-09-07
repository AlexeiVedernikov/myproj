package com.my3o.backend.service.impl;

import java.util.*;

import com.my3o.common.constant.UserType;
import com.my3o.frontend.web.model.CommonResponse;
import com.my3o.frontend.web.model.ParentChildWebModel;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my3o.backend.dao.IGroupDao;
import com.my3o.backend.dao.IOrganizationDao;
import com.my3o.backend.dao.IUserDao;
import com.my3o.backend.domain.GroupEntity;
import com.my3o.backend.domain.OrganizationEntity;
import com.my3o.backend.domain.UserEntity;
import com.my3o.backend.service.IGroupService;
import com.my3o.backend.service.IOrganizationService;
import com.my3o.backend.service.IParentService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.GroupDto;
import com.my3o.common.dto.OrganizationDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.UserSearchBean;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chudoshilkin on 22.07.14.
 */
@Service
@Transactional
public class ParentServiceImpl implements IParentService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private IGroupService groupService;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> search(UserSearchBean searchBean) throws BasicServiceException {
        List<UserDto> result = new ArrayList<UserDto>();

        List<UserEntity> entityList = userDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (UserEntity item : entityList) {
            result.add(toDto(item));
        }

        return result;
    }

    @Override
    @Transactional
    public UserDto save(UserDto studentDto, UserDto parentDto) throws BasicServiceException {
        UserEntity e = userDao.save(toEntity(studentDto, parentDto));
        return toDto(e);
    }

    @Override
    @Transactional
    public UserDto delete(UserDto studentDto, UserDto parentDto) throws BasicServiceException {
        UserEntity e = userDao.save(toEntityDelete(studentDto, parentDto));
        return toDto(e);
    }

    @Override
    public void delete(String pk) throws BasicServiceException {

    }

    @Override
    public UserDto toDtoUser(UserEntity entity) {
        UserDto userDto = new UserDto();

        userDto.setId(entity.getId());
        userDto.setName(entity.getName());
        userDto.setDescription(entity.getDescription());
        userDto.setPassword(entity.getPassword());
        userDto.setEmail(entity.getEmail());
        userDto.setLogin(entity.getEmail());
        userDto.setPhone(entity.getPhone());
        userDto.setUserType(entity.getUserType());
        userDto.setStatus(entity.getStatus());
        userDto.setCreateByUserId(entity.getCreateByUserId());
        userDto.setCreateTime(entity.getCreateTime());
        userDto.setIsFake(entity.getIsFake());
        userDto.setLastUpdateTime(entity.getLastUpdateTime());
        userDto.setUpdateByUserId(entity.getUpdateByUserId());

        List<OrganizationDto> organizationDtoList = new ArrayList<OrganizationDto>();
        List<GroupDto> groupDtoList = new ArrayList<GroupDto>();

        Iterator<GroupEntity> it = entity.getGroupEntitySet().iterator();
        while (it.hasNext()) {
            GroupEntity group = it.next();
            groupDtoList.add(groupService.toDto(group));
        }

        userDto.setGroupList(groupDtoList);

        Iterator<OrganizationEntity> ite = entity.getOrganizationEntitySet().iterator();
        while (ite.hasNext()) {
            OrganizationEntity organization = ite.next();
            organizationDtoList.add(organizationService.toDto(organization));
        }

        userDto.setOrganizationList(organizationDtoList);

        return userDto;
    }

    private UserDto toDto(UserEntity entity) {
        UserDto userDto = toDtoUser(entity);

        List<UserDto> userDtoList = new ArrayList<UserDto>();

        Iterator<UserEntity> it = null;

        if (entity.getUserType().toString().equals("Scholar")) {
            it = entity.getParentSet().iterator();
        } else if (entity.getUserType().toString().equals("Parent")) {
            it = entity.getChildrenSet().iterator();
        }

        if (it != null) {
            while (it.hasNext()) {
                UserEntity userEntity = it.next();
                userDtoList.add(toDtoUser(userEntity));
            }
        }

        userDto.setUserList(userDtoList);

        return userDto;
    }

    private UserEntity toEntity(UserDto studentDto, UserDto parentDto) throws BasicServiceException {
        UserEntity entity = userDao.findById(studentDto.getId());

        Set<UserEntity> parentSet = entity.getParentSet();

        parentSet.add(userDao.findById(parentDto.getId()));

        entity.setParentSet(parentSet);

        return entity;
    }

    private UserEntity toEntityDelete(UserDto studentDto, UserDto parentDto) throws BasicServiceException {
        UserEntity entity = userDao.findById(studentDto.getId());

        Set<UserEntity> parentSet = entity.getParentSet();

        parentSet.remove(userDao.findById(parentDto.getId()));

        entity.setParentSet(parentSet);

        return entity;
    }

    @Override
    @Transactional
    public UserDto parentAdd(HttpServletRequest request,
                             HttpServletResponse response, ParentChildWebModel dataModel) throws BasicServiceException {

        if (dataModel.getChildId() == null || dataModel.getParentId() == null) {
            return null;
        }

        UserSearchBean searchBeanStudent = new UserSearchBean(dataModel.getChildId());
        UserSearchBean searchBeanParent = new UserSearchBean(dataModel.getParentId());

        UserDto studentDto = search(searchBeanStudent).get(0);
        UserDto parentDto = search(searchBeanParent).get(0);

        UserDto parentDtoResult = save(studentDto, parentDto);

        for (UserDto dto : studentDto.getUserList()) {
            if (dto.getId().equals(parentDto.getId())) {
                parentDtoResult = null;
                break;
            }
        }

        return parentDto;
    }

    @Override
    @Transactional
    public UserDto childAdd(HttpServletRequest request,
                             HttpServletResponse response, ParentChildWebModel dataModel) throws BasicServiceException {

        if (dataModel.getChildId() == null || dataModel.getParentId() == null) {
            return null;
        }

        UserSearchBean searchBeanStudent = new UserSearchBean(dataModel.getChildId());
        UserSearchBean searchBeanParent = new UserSearchBean(dataModel.getParentId());

        UserDto studentDto = search(searchBeanStudent).get(0);
        UserDto parentDto = search(searchBeanParent).get(0);

        UserDto childDtoResult = save(studentDto, parentDto);

        for (UserDto dto : parentDto.getUserList()) {
            if (dto.getId().equals(studentDto.getId())) {
                childDtoResult = null;
            }
        }

        return childDtoResult;
    }

    @Override
    @Transactional
    public List<UserDto> searchParent(String selectOrganization) throws BasicServiceException {
        UserSearchBean userSearchBean = new UserSearchBean();
        userSearchBean.setUserType(UserType.Parent);
        List<UserDto> userList = search(userSearchBean);
        Set<UserDto> userDtoSet = new LinkedHashSet<UserDto>();

        for (UserDto dto : userList) {
            for (UserDto child : dto.getUserList()) {
                for (OrganizationDto organizationDto : child.getOrganizationList()) {
                    if (organizationDto.getId().equals(selectOrganization)) {
                        userDtoSet.add(dto);
                        break;
                    }
                }
            }
        }

        List<UserDto> dataResult = new ArrayList<UserDto>(userDtoSet);

        return dataResult;
    }

    @Override
    @Transactional
    public List<OrganizationDto> determineOrganizationParent(String userId) throws BasicServiceException {
        UserDto userDto = search(new UserSearchBean(userId)).get(0);

        Set<OrganizationDto> organizationDtoSet = new LinkedHashSet<OrganizationDto>();
        for (UserDto dto : userDto.getUserList()) {
            for (OrganizationDto organizationDto : dto.getOrganizationList()) {
                organizationDtoSet.add(organizationDto);
            }
        }

        List<OrganizationDto> dataResult = new ArrayList<OrganizationDto>(organizationDtoSet);

        return dataResult;
    }

    @Override
    public IUserDao getUserDao() {
        // TODO Auto-generated method stub
        return userDao;
    }

    @Override
    public void setUserDao(IUserDao userDao) {
        // TODO Auto-generated method stub
    }

}