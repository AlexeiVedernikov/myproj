package com.my3o.backend.service;

import com.my3o.backend.dao.IUserDao;
import com.my3o.backend.domain.UserEntity;
import com.my3o.common.dto.OrganizationDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.UserSearchBean;
import com.my3o.frontend.web.model.ParentChildWebModel;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by: shilkin
 */
public interface IParentService {
    List<UserDto> search(UserSearchBean searchBean) throws BasicServiceException;

    UserDto save(UserDto UserDto, UserDto ParentUserDto) throws BasicServiceException;

    UserDto delete(UserDto UserDto, UserDto ParentUserDto) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    public IUserDao getUserDao();

    public void setUserDao(IUserDao userDao);

    public UserDto parentAdd(HttpServletRequest request,
                             HttpServletResponse response, @RequestBody ParentChildWebModel dataModel) throws BasicServiceException;

    public UserDto childAdd(HttpServletRequest request,
                             HttpServletResponse response, @RequestBody ParentChildWebModel dataModel) throws BasicServiceException;

    public List<UserDto> searchParent(String selectOrganization) throws BasicServiceException;

    public List<OrganizationDto> determineOrganizationParent(String userId) throws BasicServiceException;

    public UserDto toDtoUser(UserEntity entity);
}
