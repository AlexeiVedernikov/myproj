package com.my3o.backend.service;

import java.util.List;

import com.my3o.backend.dao.IUserDao;
import com.my3o.backend.domain.UserEntity;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.UserSearchBean;
import com.my3o.frontend.web.model.UserWebModel;

/**
 * Created by: andrew
 */
public interface IUserService {
    List<UserDto> search(UserSearchBean searchBean) throws BasicServiceException;
//    List<UserDto> searchSome(UserSearchBean searchBean) throws BasicServiceException;

    // UserDto save(UserDto userDto) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    public UserDto toDto(UserEntity entity);

    public UserEntity toEntity(UserDto dto) throws BasicServiceException;

    public IUserDao getUserDao();

    public void setUserDao(IUserDao userDao);

    public UserDto register(UserWebModel dataModel, String loginUserId) throws Exception;

}
