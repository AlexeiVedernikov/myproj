package com.my3o.backend.service.impl;

import java.io.IOException;
import java.util.*;

import com.my3o.backend.service.*;
import com.my3o.common.constant.Status;
import com.my3o.common.dto.*;
import com.my3o.common.searchbean.JournalSearchBean;
import com.my3o.common.searchbean.SheduleSearchBean;
import com.my3o.common.searchbean.UserSearchBean;
import com.my3o.sms24x7.SMS24x7Impl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my3o.backend.dao.IDistrictDao;
import com.my3o.backend.dao.INotificationDao;
import com.my3o.backend.dao.IRegionDao;
import com.my3o.backend.dao.ITownDao;
import com.my3o.backend.domain.NotificationEntity;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.NotificationSearchBean;
import com.my3o.mail.impl.Sender;

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
@Service
@Transactional
public class NotificationServiceImpl implements INotificationService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private INotificationDao notificationDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISheduleService sheduleService;

    @Autowired
    private IJournalService journalService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private ISettingMailService settingMailService;

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> search(NotificationSearchBean searchBean) throws BasicServiceException {
        List<NotificationDto> result = new ArrayList<NotificationDto>();

        List<NotificationEntity> entityList = notificationDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (NotificationEntity item : entityList) {
            result.add(toDto(item));
        }

        return result;
    }

    @Override
    // @Transactional(readOnly = false)
    public NotificationDto save(NotificationDto notificationDto) throws BasicServiceException {
        // townDao.save(toEntity(townDto));
        NotificationEntity e = notificationDao.save(toEntity(notificationDto));
        return toDto(e);
    }

    @Override
    public void delete(String pk) throws BasicServiceException {
        notificationDao.delete(notificationDao.findById(pk));
    }

    public NotificationEntity toEntity(NotificationDto dto) {
        NotificationEntity entity = new NotificationEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setText(dto.getText());
        entity.setDateOn(dto.getDateOn());
        entity.setNotificationType(dto.getNotificationType());
        entity.setStatus(dto.getStatus());

        entity.setUserType(dto.getUserType());

        entity.setCreateTime(dto.getCreateTime());
        entity.setLastUpdateTime(dto.getLastUpdateTime());

        entity.setCreateByUserId(dto.getCreateByUserId());
        entity.setUpdateByUserId(dto.getUpdateByUserId());

        entity.setSettingMail(settingMailService.getSettingMailDao().findById(dto.getSettingMailDto().getId()));
        entity.setUser(userService.getUserDao().findById(dto.getUserDto().getId()));
        entity.setGroup(groupService.getGroupDao().findById(dto.getGroupDto().getId()));
        entity.setOrganization(organizationService.getOrganizationDao().findById(dto.getOrganizationDto().getId()));

        return entity;
    }

    public NotificationDto toDto(NotificationEntity entity) {
        NotificationDto dto = new NotificationDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setText(entity.getText());
        dto.setDateOn(entity.getDateOn());
        dto.setNotificationType(entity.getNotificationType());
        dto.setStatus(entity.getStatus());

        dto.setUserType(entity.getUserType());

        dto.setCreateTime(entity.getCreateTime());
        dto.setLastUpdateTime(entity.getLastUpdateTime());

        dto.setCreateByUserId(entity.getCreateByUserId());
        dto.setUpdateByUserId(entity.getUpdateByUserId());

        if (entity.getSettingMail() != null) {
            dto.setSettingMailDto(settingMailService.toDto(entity.getSettingMail()));
        }

        if (entity.getUser() != null) {
            dto.setUserDto(userService.toDto(entity.getUser()));
        }
        if (entity.getGroup() != null) {
            dto.setGroupDto(groupService.toDto(entity.getGroup()));
        }
        if (entity.getOrganization() != null) {
            dto.setOrganizationDto(organizationService.toDto(entity.getOrganization()));
        }

        return dto;
    }

    public void sendMessage(SettingMailDto smd, UserDto ud, NotificationDto nDto, UserDto childrenDto) {

        Sender newww = new Sender(smd.getUserName(), smd.getUserPassword(), smd.getHost(), smd.getPort());
        if (nDto.getText().equals("marksmarksmarks")) {
            String text = buildMarks(ud, nDto, childrenDto);
            newww.send(smd.getDefaultSender() + ": " + smd.getDefaultSubjectPrefix(), text, ud.getEmail());
        } else {
            newww.send(smd.getDefaultSender() + ": " + smd.getDefaultSubjectPrefix(), nDto.getText(), ud.getEmail());
        }

    }

    public void sendSms(UserDto ud, NotificationDto nDto, UserDto childrenDto) {

        if (nDto.getText().equals("marksmarksmarks")) {
            String text = buildMarksForSms(ud, nDto, childrenDto);
            SMS24x7Impl sms24x7 = new SMS24x7Impl();
            try {
                sms24x7.login("info.my3o@gmail.com", "XcGY3F4");
                sms24x7.send("my3o", "+" + ud.getPhone(), text);
                sms24x7.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            SMS24x7Impl sms24x7 = new SMS24x7Impl();
            try {
                sms24x7.login("info.my3o@gmail.com", "XcGY3F4");
                sms24x7.send("my3o", ud.getPhone(), nDto.getText());
                sms24x7.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public String buildMarks(UserDto ud, NotificationDto nDto, UserDto childrenDto) {
        StringBuilder str = new StringBuilder();
        str.append("<p>Добрый день, " + ud.getName() + ".</p>" + "<p>На этой недели ученик(ца) " + childrenDto.getName() +
                    " получил(а) следующие оценки:</p>");

        List<SheduleDto> sheduleDtoList = new ArrayList<SheduleDto>();

        try {
            UserDto userDto = new UserDto();
            UserSearchBean userSearchBean = new UserSearchBean(childrenDto.getId());
            userDto = userService.search(userSearchBean).get(0);

            SheduleSearchBean sheduleSearchBean = new SheduleSearchBean();
            sheduleSearchBean.setGroupId(userDto.getGroupList().get(0).getId());

            Integer year = nDto.getDateOn().getYear() + 1900;
            if (nDto.getDateOn().getMonth() < 7) {
                year = year - 1;
            }

            sheduleSearchBean.setYear(String.valueOf(year));
            sheduleSearchBean.setStatus(Status.Active);
            sheduleDtoList = sheduleService.search(sheduleSearchBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JournalSearchBean journalSearchBean = new JournalSearchBean();
        journalSearchBean.setStatus(Status.Active);
        List<JournalDto> journalDtoList = new ArrayList<JournalDto>();

        journalSearchBean.setUserId(childrenDto.getId());

        if (StringUtils.isNotBlank(nDto.getDate())) {
            Date dateDate = new Date();
            dateDate.setDate(Integer.valueOf(nDto.getDate().substring(0, 2)));
            dateDate.setMonth(Integer.valueOf(nDto.getDate().substring(3, 5)) - 1);
            dateDate.setYear(Integer.valueOf(nDto.getDate().substring(6)) - 1900);

            for (int i=0; i<7; i++) {
                String currentDay = String.valueOf(dateDate.getDate());
                if (dateDate.getDate() < 10) {
                    currentDay = "0" + currentDay;
                }
                String currentMonth = String.valueOf(dateDate.getMonth()+1);
                if (dateDate.getMonth() < 9) {
                    currentMonth = "0" + currentMonth;
                }
                journalSearchBean.addDate(currentDay + "-" + currentMonth + "-" + String.valueOf(dateDate.getYear()+1900));
                dateDate.setDate(dateDate.getDate() + 1);
            }
        }

        try {
            journalDtoList = journalService.search(journalSearchBean);
        } catch (BasicServiceException e) {
            e.printStackTrace();
        }

        str.append("<table border='1' cellpadding='9' rules='all'><thead><tr><th>Предметы</th>");

        for (String strDate : journalSearchBean.getDateList()) {
            str.append("<th>" + strDate +"</th>");
        }

        str.append("</tr></thead><tbody><tr>");

        for (SheduleDto sheduleDto : sheduleDtoList) {
            str.append("<td>" + sheduleDto.getDisciplineDto().getName() + "</td>");

            for (String strDate : journalSearchBean.getDateList()) {
                int keyMark = 0;
                for (JournalDto journalDto : journalDtoList) {
                    if (journalDto.getDate().equals(strDate) && journalDto.getSheduleDto().getId().equals(sheduleDto.getId())) {
                        str.append("<td align='center'>" + journalDto.getMark() +"</td>");
                        keyMark = 1;
                        break;
                    }
                }
                if (keyMark == 0) {
                    str.append("<td></td>");
                }
            }
            str.append("</tr><tr>");
        }

        str.append("</tr></tbody></table>");

        str.append("<p>С уважением, команда \"Вся школа\".");
        return str.toString();
    }

    public String buildMarksForSms(UserDto ud, NotificationDto nDto, UserDto childrenDto) {
        StringBuilder str = new StringBuilder();

        List<SheduleDto> sheduleDtoList = new ArrayList<SheduleDto>();
        Translit translit = new Translit();

        try {
            UserDto userDto = new UserDto();
            UserSearchBean userSearchBean = new UserSearchBean(childrenDto.getId());
            userDto = userService.search(userSearchBean).get(0);

            SheduleSearchBean sheduleSearchBean = new SheduleSearchBean();
            sheduleSearchBean.setGroupId(userDto.getGroupList().get(0).getId());

            Integer year = nDto.getDateOn().getYear() + 1900;
            if (nDto.getDateOn().getMonth() < 7) {
                year = year - 1;
            }

            sheduleSearchBean.setYear(String.valueOf(year));
            sheduleSearchBean.setStatus(Status.Active);
            sheduleDtoList = sheduleService.search(sheduleSearchBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JournalSearchBean journalSearchBean = new JournalSearchBean();
        journalSearchBean.setStatus(Status.Active);
        List<JournalDto> journalDtoList = new ArrayList<JournalDto>();

        journalSearchBean.setUserId(childrenDto.getId());

        if (StringUtils.isNotBlank(nDto.getDate())) {
            Date dateDate = new Date();
            dateDate.setDate(Integer.valueOf(nDto.getDate().substring(0, 2)));
            dateDate.setMonth(Integer.valueOf(nDto.getDate().substring(3, 5)) - 1);
            dateDate.setYear(Integer.valueOf(nDto.getDate().substring(6)) - 1900);

            for (int i=0; i<6; i++) {
                String currentDay = String.valueOf(dateDate.getDate());
                if (dateDate.getDate() < 10) {
                    currentDay = "0" + currentDay;
                }
                String currentMonth = String.valueOf(dateDate.getMonth()+1);
                if (dateDate.getMonth() < 9) {
                    currentMonth = "0" + currentMonth;
                }
                journalSearchBean.addDate(currentDay + "-" + currentMonth + "-" + String.valueOf(dateDate.getYear()+1900));
                dateDate.setDate(dateDate.getDate() + 1);
            }
        }

        try {
            journalDtoList = journalService.search(journalSearchBean);
        } catch (BasicServiceException e) {
            e.printStackTrace();
        }

        for (SheduleDto sheduleDto : sheduleDtoList) {
            StringBuilder strSheduleMarks = new StringBuilder();
            try {
                String a = translit.toTranslit(sheduleDto.getDisciplineDto().getName());
                if (a.length() > 4) {
                    a = a.substring(0, 4);
                }
                strSheduleMarks.append(a + ":");
            } catch (IOException e) {
                e.printStackTrace();
            }

            int keyMark = 0;

            for (String strDate : journalSearchBean.getDateList()) {
                for (JournalDto journalDto : journalDtoList) {
                    if (journalDto.getDate().equals(strDate) && journalDto.getSheduleDto().getId().equals(sheduleDto.getId())) {
                        strSheduleMarks.append(journalDto.getMark() +",");
                        keyMark = 1;
                        break;
                    }
                }
            }

            if (keyMark == 1) {
                str.append(strSheduleMarks);
            }
        }
        str.deleteCharAt(str.length()-1);
        return str.toString();
    }

    public INotificationDao getNotificationDao() {
        return notificationDao;
    }

    @Override
    public void setNotificationDao(INotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

}
