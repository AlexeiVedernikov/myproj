package com.my3o.frontend.web.controller;

import com.my3o.backend.service.IOrganizationService;
import com.my3o.backend.service.ISettingMailService;
import com.my3o.backend.service.IUserService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.OrganizationDto;
import com.my3o.common.dto.SettingMailDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.SettingMailSearchBean;
import com.my3o.common.searchbean.UserSearchBean;
import com.my3o.frontend.constant.My3oURL;
import com.my3o.frontend.web.model.CommonResponse;
import com.my3o.frontend.web.model.SettingMailWebModel;
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
import java.util.List;

/**
 * @author anton
 * 
 */
@Controller
public class SettingMailController extends AbstractFrontendController {

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private ISettingMailService settingMailService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = My3oURL.SETTING_MAIL_LIST, method = RequestMethod.GET)
    public String getSettingMailView(HttpServletRequest request, Model model, HttpSession session) {

        try {
            String userType = this.getUserRole(request);
            String userId = this.getUserId(request);
            String selectOrganization = this.cookieProvider.getOrganizationId(request);
            List<SettingMailDto> settingMailDtoList = new ArrayList<SettingMailDto>();
            List<OrganizationDto> organizationDtoList = new ArrayList<OrganizationDto>();

            if (StringUtils.isNotBlank(selectOrganization) && !selectOrganization.equals("disabled")) {
                SettingMailSearchBean settingMailSearchBean = new SettingMailSearchBean();
                settingMailSearchBean.setOrganizationId(selectOrganization);
                settingMailDtoList = settingMailService.search(settingMailSearchBean);

            } else if (userType.equals("SuperAdmin")) {
                settingMailDtoList = settingMailService.search(new SettingMailSearchBean());

            } else {
                UserSearchBean userSearchBean = new UserSearchBean();
                userSearchBean.addKey(userId);
                UserDto userDto = userService.search(userSearchBean).get(0);

                organizationDtoList = userDto.getOrganizationList();

                SettingMailSearchBean settingMailSearchBean = new SettingMailSearchBean();

                for (OrganizationDto organizationDto : organizationDtoList) {
                    settingMailSearchBean.setOrganizationId(organizationDto.getId());
                    try {
                        settingMailDtoList.addAll(settingMailService.search(settingMailSearchBean));
                    } catch (BasicServiceException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }

            model.addAttribute("settingMailList", settingMailDtoList);

        } catch (BasicServiceException e) {
            log.error(e.getMessage(), e);
        }

        return "setting-mail";
    }

    @RequestMapping(value = My3oURL.SETTING_MAIL, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<SettingMailDto> postSettingMail(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody SettingMailWebModel dataModel) throws Exception {
        CommonResponse<SettingMailDto> commonResponse = new CommonResponse<SettingMailDto>();
        SettingMailDto settingMailDto = new SettingMailDto();

        if ("-1".equals(dataModel.getId())) {
            settingMailDto.setId(null);
        } else {
            settingMailDto.setId(dataModel.getId());
        }
        settingMailDto.setName(dataModel.getName());
        settingMailDto.setDescription(dataModel.getDescription());
        settingMailDto.setHost(dataModel.getHost());
        settingMailDto.setPort(dataModel.getPort());
        settingMailDto.setUserName(dataModel.getUserName());
        settingMailDto.setUserPassword(dataModel.getUserPassword());
        settingMailDto.setDefaultSender(dataModel.getDefaultSender());
        settingMailDto.setDefaultSubjectPrefix(dataModel.getDefaultSubjectPrefix());
        settingMailDto.setStatus(dataModel.getStatus());
        settingMailDto.setOrganizationDto(new OrganizationDto(dataModel.getOrganizationId()));
        commonResponse.setValue(settingMailService.save(settingMailDto));

        // Sender newww = new Sender(settingMailDto.getUserName(),
        // settingMailDto.getUserPassword());
        // newww.send(settingMailDto.getDefaultSubjectPrefix(), "hello",
        // settingMailDto.getDefaultSender(),
        // "anton.kovtun93@gmail.com");

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.SETTING_MAIL, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> deleteSettingMail(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody SettingMailWebModel dataModel) throws Exception {
        settingMailService.delete(dataModel.getId());

        CommonResponse<String> commonResponse = new CommonResponse<String>();
        commonResponse.setErrorCode(ErrorCodes.OK);
        return commonResponse;
    }

    @RequestMapping(value = My3oURL.SETTING_MAIL_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<SettingMailDto>> searchSettingMail(HttpSession session, HttpServletRequest request,
                                                             HttpServletResponse response) throws Exception {

        CommonResponse<List<SettingMailDto>> commonResponse = new CommonResponse<List<SettingMailDto>>();

        return commonResponse;
    }

}
