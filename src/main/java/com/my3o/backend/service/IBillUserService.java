package com.my3o.backend.service;

import java.util.List;

import com.my3o.backend.dao.IBillUserDao;
import com.my3o.backend.domain.BillUserEntity;
import com.my3o.common.dto.BillUserDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.BillUserSearchBean;

/**
 * @author anton
 * 
 */
public interface IBillUserService {
    List<BillUserDto> search(BillUserSearchBean searchBean) throws BasicServiceException;

    BillUserDto save(BillUserDto billUserDto) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    public BillUserDto toDto(BillUserEntity entity);

    public BillUserEntity toEntity(BillUserDto dto);

    public IBillUserDao getBillUserDao();

    public void setBillUserDao(IBillUserDao billUserDao);

    void bill(BillUserDto billUserDto, UserDto userDto) throws Exception;

}
