package com.my3o.frontend.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my3o.common.constant.*;
import com.my3o.frontend.constant.My3oURL;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.my3o.backend.service.IGroupService;
import com.my3o.backend.service.INotificationService;
import com.my3o.backend.service.IOrganizationService;
import com.my3o.backend.service.IParentService;
import com.my3o.backend.service.ISettingMailService;
import com.my3o.backend.service.IUserService;
import com.my3o.common.constant.NotificationUserType;
import com.my3o.common.dto.*;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.NotificationSearchBean;
import com.my3o.common.searchbean.SettingMailSearchBean;
import com.my3o.common.searchbean.UserSearchBean;
import com.my3o.frontend.constant.My3oURL;
import com.my3o.frontend.web.model.CommonResponse;
import com.my3o.frontend.web.model.NotificationWebModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author anton
 * 
 */
@Controller
public class NotificationController extends AbstractFrontendController {

    @Autowired
    private IUserService userService;

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private ISettingMailService settingMailService;

    @Autowired
    private IParentService parentService;

    @RequestMapping(value = My3oURL.NOTIFICATION_LIST, method = RequestMethod.GET)
    public String notificationView(HttpServletRequest request, Model model, HttpSession session) {

        String selectOrganization = this.cookieProvider.getOrganizationId(request);
        List<SettingMailDto> settingMailDtoList = new ArrayList<SettingMailDto>();

        try {
            if (StringUtils.isNotBlank(selectOrganization) && !selectOrganization.equals("disabled")) {
                SettingMailSearchBean settingMailSearchBean = new SettingMailSearchBean();
                settingMailSearchBean.setOrganizationId(selectOrganization);
                settingMailDtoList = settingMailService.search(settingMailSearchBean);
            }

            model.addAttribute("settingMailList", settingMailDtoList);

        } catch (BasicServiceException e) {
            log.error(e.getMessage(), e);
        }

        return "notification";
    }

    @RequestMapping(value = My3oURL.NOTIFICATION, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<NotificationDto> postNotification(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody NotificationWebModel dataModel) throws Exception {
        CommonResponse<NotificationDto> commonResponse = new CommonResponse<NotificationDto>();
        NotificationDto notificationDto = new NotificationDto();

        dataModel.getId();

        if ("-1".equals(dataModel.getId())) {
            notificationDto.setId(null);
        } else {
            notificationDto.setId(dataModel.getId());
        }

        notificationDto.setName(dataModel.getName());
        notificationDto.setDescription(dataModel.getDescription());
        notificationDto.setDateOn(dataModel.getData());
        notificationDto.setText(dataModel.getText());
        notificationDto.setStatus(dataModel.getStatus());
        notificationDto.setNotificationType(dataModel.getType());
        notificationDto.setUserType(dataModel.getUserType());
        notificationDto.setSettingMailDto(new SettingMailDto(dataModel.getSettingMailId()));
        notificationDto.setUserDto(new UserDto(dataModel.getUserId()));
        notificationDto.setGroupDto(new GroupDto(dataModel.getGroupId()));
        notificationDto.setOrganizationDto(new OrganizationDto(dataModel.getOrganizationId()));
        notificationDto.setDate(dataModel.getDate());

        NotificationSearchBean nsb = new NotificationSearchBean();
        NotificationDto dateDto = new NotificationDto();
        nsb.addKey(dataModel.getId());
        try {
            dateDto = notificationService.search(nsb).get(0);
            notificationDto.setCreateTime(dateDto.getCreateTime());
            notificationDto.setLastUpdateTime(Calendar.getInstance().getTime());
        } catch (BasicServiceException e) {
            notificationDto.setCreateTime(Calendar.getInstance().getTime());
        }

        String loginUserId = null;
        loginUserId = this.getUserId(request);

        NotificationSearchBean nsb1 = new NotificationSearchBean();
        NotificationDto userCreateDto = new NotificationDto();
        nsb1.addKey(dataModel.getId());
        try {
            userCreateDto = notificationService.search(nsb1).get(0);
            notificationDto.setCreateByUserId(userCreateDto.getCreateByUserId());
            notificationDto.setUpdateByUserId(loginUserId);
        } catch (BasicServiceException e) {
            notificationDto.setCreateByUserId(loginUserId);
        }

        SettingMailSearchBean smSearchBean = new SettingMailSearchBean();
        SettingMailDto smDto = new SettingMailDto();
        smSearchBean.addKey(dataModel.getSettingMailId());
        try {
            smDto = settingMailService.search(smSearchBean).get(0);
        } catch (BasicServiceException e) {
            log.error(toString());
        }

        if (!dataModel.getSettingMailId().equals("disabled") && !dataModel.getOrganizationId().equals("disabled")
                && !dataModel.getGroupId().equals("disabled") && !dataModel.getUserId().equals("disabled")
                && dataModel.getType() == NotificationType.email) {
            // send only selected user

            UserSearchBean usb = new UserSearchBean();
            UserDto userDto = new UserDto();
            usb.addKey(dataModel.getUserId());
            if (dataModel.getUserType().equals(NotificationUserType.Parent)) {
                try {
                    userDto = parentService.search(usb).get(0);
                } catch (BasicServiceException e) {
                    log.error(toString());
                }
                if (userDto.getUserList() != null) {
                    for (UserDto parentDto : userDto.getUserList()) {
                        if(parentDto.getIsFake() == false) {
                            try {
                                notificationService.sendMessage(smDto, parentDto, notificationDto, userDto);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
//            else if (dataModel.getUserType().equals(NotificationUserType.Scholar)){
//                try{
//                    userDto = userService.search(usb).get(0);
//                    notificationService.sendMessage(smDto, userDto, notificationDto, userDto);
//                } catch (BasicServiceException e){
//                    log.error(toString());
//                }
//            } else if (dataModel.getUserType().equals(NotificationUserType.All)){
//                try{
//                    userDto = userService.search(usb).get(0);
//                    notificationService.sendMessage(smDto, userDto, notificationDto, userDto);
//                } catch (BasicServiceException e){
//                    log.error(toString());
//                }
//                try {
//                    userDto = parentService.search(usb).get(0);
//                } catch (BasicServiceException e) {
//                    log.error(toString());
//                }
//                if (userDto.getUserList() != null) {
//                    for (UserDto parentDto : userDto.getUserList()) {
//                        try {
//                            notificationService.sendMessage(smDto, parentDto, notificationDto, userDto);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }

        } else if (!dataModel.getSettingMailId().equals("disabled")
                && !dataModel.getOrganizationId().equals("disabled") && !dataModel.getGroupId().equals("disabled")
                && dataModel.getType() == NotificationType.email) {
            // send only selected group

            List<UserDto> dataList = new ArrayList<UserDto>();
            UserSearchBean usb = new UserSearchBean();
            usb.setGroupId(dataModel.getGroupId());

            List<UserDto> userList = new ArrayList<UserDto>();
            if (dataModel.getUserType().equals(NotificationUserType.Parent)) {
                usb.setUserType(UserType.Scholar);
                userList = parentService.search(usb);
            }
//            else if (dataModel.getUserType().equals(NotificationUserType.Scholar)){
//                usb.setUserType(UserType.Scholar);
//                userList = userService.search(usb);
//            } else if (dataModel.getUserType().equals(NotificationUserType.All)){
//                userList = userService.search(new UserSearchBean());
//            }

            session.setAttribute("notificationSizeMax", userList.size());
            Integer notificationCount = 0;
            session.setAttribute("notificationCount", notificationCount);
            for (UserDto dto : userList) {
                if (dto.getUserList() != null) {
                    for (UserDto parentDto : dto.getUserList()) {
                        if(parentDto.getIsFake() == false) {
                            try {
                                notificationService.sendMessage(smDto, parentDto, notificationDto, dto);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
//                else {
//                    try {
//                        notificationService.sendMessage(smDto, dto, notificationDto, dto);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
                session.setAttribute("notificationCount", ++notificationCount);
            }

        } else if (!dataModel.getSettingMailId().equals("disabled")
                && !dataModel.getOrganizationId().equals("disabled") && dataModel.getType() == NotificationType.email) {
            // send only selected organization

            UserSearchBean usb = new UserSearchBean();
            usb.setUserType(UserType.Scholar);
            usb.setOrganizationId(dataModel.getOrganizationId());
            List<UserDto> userList = parentService.search(usb);

            session.setAttribute("notificationSizeMax", userList.size());
            Integer notificationCount = 0;
            session.setAttribute("notificationCount", notificationCount);
            for (UserDto dto : userList) {
                if (dto.getUserList() != null) {
                    for (UserDto parentDto : dto.getUserList()) {
                        if(parentDto.getIsFake() == false) {
                            try {
                                notificationService.sendMessage(smDto, parentDto, notificationDto, dto);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                session.setAttribute("notificationCount", ++notificationCount);
            }

        } else if (!dataModel.getSettingMailId().equals("disabled") && !dataModel.getOrganizationId().equals("disabled")
                && !dataModel.getGroupId().equals("disabled") && !dataModel.getUserId().equals("disabled")
                && dataModel.getType() == NotificationType.sms) {
            // send sms only selected user

            UserSearchBean usb = new UserSearchBean();
            UserDto userDto = new UserDto();
            usb.addKey(dataModel.getUserId());
            if (dataModel.getUserType().equals(NotificationUserType.Parent)) {
                try {
                    userDto = parentService.search(usb).get(0);
                } catch (BasicServiceException e) {
                    log.error(toString());
                }
                if (userDto.getUserList() != null) {
                    for (UserDto parentDto : userDto.getUserList()) {
                        if (parentDto.getIsFake() == false) {
                            try {
                                notificationService.sendSms(parentDto, notificationDto, userDto);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } else if (!dataModel.getOrganizationId().equals("disabled") && dataModel.getType() == NotificationType.sms
                && !dataModel.getSettingMailId().equals("disabled") && !dataModel.getGroupId().equals("disabled")) {
            // send sms only selected group

            UserSearchBean usb = new UserSearchBean();
            usb.setGroupId(dataModel.getGroupId());
            List<UserDto> userList = new ArrayList<UserDto>();
            usb.setUserType(UserType.Scholar);
            userList = parentService.search(usb);

            session.setAttribute("notificationSizeMax", userList.size());
            Integer notificationCount = 0;
            session.setAttribute("notificationCount", notificationCount);
            for (UserDto dto : userList) {
                if (dto.getUserList() != null) {
                    for (UserDto parentDto : dto.getUserList()) {
                        try {
                            notificationService.sendSms(parentDto, notificationDto, dto);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                session.setAttribute("notificationCount", ++notificationCount);
            }

        } else if (!dataModel.getOrganizationId().equals("disabled") && dataModel.getType() == NotificationType.sms
                && !dataModel.getSettingMailId().equals("disabled")) {
            // send sms only selected organization

            UserSearchBean usb = new UserSearchBean();
            usb.setUserType(UserType.Scholar);
            usb.setOrganizationId(dataModel.getOrganizationId());
            List<UserDto> userList = parentService.search(usb);

            session.setAttribute("notificationSizeMax", userList.size());
            Integer notificationCount = 0;
            session.setAttribute("notificationCount", notificationCount);
            for (UserDto dto : userList) {
                if (dto.getUserList() != null) {
                    for (UserDto parentDto : dto.getUserList()) {
                        try {
                            notificationService.sendSms(parentDto, notificationDto, dto);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                session.setAttribute("notificationCount", ++notificationCount);
            }

        }

        commonResponse.setValue(notificationService.save(notificationDto));

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.NOTIFICATION, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> doDeleteNotification(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody NotificationWebModel dataModel) throws Exception {

        notificationService.delete(dataModel.getId());

        CommonResponse<String> commonResponse = new CommonResponse<String>();
        commonResponse.setErrorCode(ErrorCodes.OK);
        return commonResponse;
    }

    @RequestMapping(value = My3oURL.NOTIFICATION_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<NotificationDto>> searchNotification(HttpSession session, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {

        CommonResponse<List<NotificationDto>> commonResponse = new CommonResponse<List<NotificationDto>>();

        String userType = this.getUserRole(request);
        String userId = this.getUserId(request);
        String selectOrganization = this.cookieProvider.getOrganizationId(request);
        List<OrganizationDto> organizationDtoList = new ArrayList<OrganizationDto>();
        List<NotificationDto> notificationDtoList = new ArrayList<NotificationDto>();

        try {
            UserSearchBean userSearchBean = new UserSearchBean();
            userSearchBean.addKey(userId);
            UserDto userDto = userService.search(userSearchBean).get(0);
            organizationDtoList = userDto.getOrganizationList();
        } catch (BasicServiceException e) {
            log.error(e.getMessage(), e);
        }

        try {
            NotificationSearchBean notificationSearchBeanOrg = new NotificationSearchBean();

            if (StringUtils.isNotBlank(selectOrganization) && !selectOrganization.equals("disabled")) {
                notificationSearchBeanOrg.setOrganizationId(selectOrganization);
                try {
                    notificationDtoList.addAll(notificationService.search(notificationSearchBeanOrg));
                } catch (BasicServiceException e) {
                    log.error(e.getMessage(), e);
                }

            } else if (userType.equals("SuperAdmin")) {
                notificationDtoList.addAll(notificationService.search(new NotificationSearchBean()));

            } else if (userType.equals("SchoolAdmin")) {
                for (OrganizationDto organizationDto : organizationDtoList) {
                    notificationSearchBeanOrg.setOrganizationId(organizationDto.getId());
                    try {
                        notificationDtoList.addAll(notificationService.search(notificationSearchBeanOrg));
                    } catch (BasicServiceException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }
        } catch (BasicServiceException e) {
            log.error(e.getMessage(), e);
        }

        commonResponse.setValue(notificationDtoList);

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.PROGRESS_NOTIFICATION_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<Integer>> searchNotificationProgress(HttpSession session, HttpServletRequest request,
                                                             HttpServletResponse response) throws Exception {

        CommonResponse<List<Integer>> commonResponse = new CommonResponse<List<Integer>>();

        List<Integer> progressNoty = new ArrayList<Integer>();

        Integer notySizeMax = null;
        notySizeMax = (Integer) session.getAttribute("notificationSizeMax");

        if (notySizeMax != null) {
            progressNoty.add(notySizeMax);
        }

        Integer notyCount = null;
        notyCount = (Integer) session.getAttribute("notificationCount");

        if (notySizeMax != null) {
            progressNoty.add(notyCount);
        }

        commonResponse.setValue(progressNoty);

        return commonResponse;
    }

}
