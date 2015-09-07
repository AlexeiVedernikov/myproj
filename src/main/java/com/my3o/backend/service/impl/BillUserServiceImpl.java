package com.my3o.backend.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my3o.backend.config.IBackendProperties;
import com.my3o.backend.dao.IBillUserDao;
import com.my3o.backend.dao.IUserDao;
import com.my3o.backend.domain.BillUserEntity;
import com.my3o.backend.service.IBillUserService;
import com.my3o.backend.service.IUserService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.BillUserDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.BillUserSearchBean;
import com.my3o.rest.client.impl.QiwiClient;
import com.my3o.rest.response.QiwiResponseWrapper;

@Service
@Transactional
public class BillUserServiceImpl implements IBillUserService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IBillUserDao billUserDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private QiwiClient qiwiClient;

    @Autowired
    private IBackendProperties backendProperties;

    @Override
    @Transactional(readOnly = true)
    public List<BillUserDto> search(BillUserSearchBean searchBean) throws BasicServiceException {
        List<BillUserDto> result = new ArrayList<BillUserDto>();

        List<BillUserEntity> entityList = billUserDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (BillUserEntity item : entityList) {
            result.add(toDto(item));
        }

        return result;
    }

    @Override
    public void bill(BillUserDto billUserDto, UserDto userDto) throws Exception {
        BillUserDto bud = save(billUserDto);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        QiwiResponseWrapper resp = qiwiClient.bill(backendProperties.getQiwiPrvId(), userDto.getPhone(),
                backendProperties.getQiwiAmount(), billUserDto.getCcy(), backendProperties.getQiwiComment(),
                sdf.format(bud.getLifetime()), null, backendProperties.getQiwiPrvName(), bud.getId());
    }

    @Override
    public BillUserDto save(BillUserDto billUserDto) throws BasicServiceException {
        BillUserEntity e = billUserDao.save(toEntity(billUserDto));
        return toDto(e);
    }

    @Override
    public void delete(String pk) throws BasicServiceException {
        billUserDao.delete(billUserDao.findById(pk));
    }

    public BillUserEntity toEntity(BillUserDto dto) {
        BillUserEntity entity = new BillUserEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setQiwiStatus(dto.getQiwiStatus());
        entity.setUserPhone(dto.getUserPhone());
        entity.setAmount(dto.getAmount());
        entity.setCcy(dto.getCcy());
        entity.setComment(dto.getComment());
        entity.setLifetime(dto.getLifetime());
        entity.setPaySource(dto.getPaySource());
        entity.setPrvName(dto.getPrvName());
        entity.setPrvId(dto.getPrvId());
        entity.setUser(userService.getUserDao().findById(dto.getUserDto().getId()));

        return entity;
    }

    public BillUserDto toDto(BillUserEntity entity) {
        BillUserDto dto = new BillUserDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setQiwiStatus(entity.getQiwiStatus());
        dto.setUserPhone(entity.getUserPhone());
        dto.setAmount(entity.getAmount());
        dto.setCcy(entity.getCcy());
        dto.setComment(entity.getComment());
        dto.setLifetime(entity.getLifetime());
        dto.setPaySource(entity.getPaySource());
        dto.setPrvName(entity.getPrvName());
        dto.setPrvId(entity.getPrvId());

        if (entity.getUser() != null) {
            dto.setUserDto(userService.toDto(entity.getUser()));
        }

        return dto;
    }

    public IBillUserDao getBillUserDao() {
        return billUserDao;
    }

    @Override
    public void setBillUserDao(IBillUserDao billUserDao) {
        this.billUserDao = billUserDao;
    }

}
