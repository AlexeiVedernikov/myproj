package com.my3o.frontend.web.controller;

import com.my3o.backend.service.IDisciplineService;
import com.my3o.backend.service.IGroupService;
import com.my3o.backend.service.ISheduleService;
import com.my3o.backend.service.IUserService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.constant.Status;
import com.my3o.common.constant.UserType;
import com.my3o.common.dto.GroupDto;
import com.my3o.common.dto.OrganizationDto;
import com.my3o.common.dto.SheduleDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.DisciplineSearchBean;
import com.my3o.common.searchbean.GroupSearchBean;
import com.my3o.common.searchbean.SheduleSearchBean;
import com.my3o.common.searchbean.UserSearchBean;
import com.my3o.frontend.constant.My3oURL;
import com.my3o.frontend.web.model.CommonResponse;
import com.my3o.frontend.web.model.SheduleWebModel;
import com.my3o.frontend.web.model.UserWebModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SheduleController extends AbstractFrontendController {

    @Autowired
    private IDisciplineService disciplineService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private ISheduleService sheduleService;

    @RequestMapping(value = My3oURL.SHEDULE_LIST, method = RequestMethod.GET)
    public String sheduleView(HttpServletRequest request, Model model, @RequestParam(value = "groupId", required = false) String groupId) {

        try {
            model.addAttribute("disciplineList", disciplineService.search(new DisciplineSearchBean()));
        } catch (BasicServiceException e) {
            log.error(e.getMessage(), e);
        }

        List<UserDto> teacherList = new ArrayList<UserDto>();

        UserSearchBean userSearchBean = new UserSearchBean();
        userSearchBean.setUserType(UserType.Teacher);

        GroupSearchBean groupSearchBean = new GroupSearchBean();
        groupSearchBean.addKey(groupId);

        if (groupId != null) {

            String organizationId = null;
            GroupDto groupDto = null;

            try {
                groupDto = groupService.search(groupSearchBean).get(0);
                userSearchBean.setOrganizationId(groupDto.getOrganizationDto().getId());
                teacherList = userService.search(userSearchBean);
            } catch (BasicServiceException e) {
                e.printStackTrace();
            }

            model.addAttribute("teacherList", teacherList);
            model.addAttribute("groupName", groupDto);
        }

        return "shedule";
    }

    @RequestMapping(value = My3oURL.SHEDULE, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<SheduleDto> postShedule(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                     @RequestBody SheduleWebModel dataModel) throws Exception {
        CommonResponse<SheduleDto> commonResponse = new CommonResponse<SheduleDto>();

        commonResponse.setValue(sheduleService.addAndEdit(dataModel));

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.SHEDULE, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> deleteShedule(HttpServletRequest request,
                                          HttpServletResponse response, @RequestBody SheduleWebModel dataModel) throws Exception {

        sheduleService.delete(dataModel.getId());

        CommonResponse<String> commonResponse = new CommonResponse<String>();

        commonResponse.setErrorCode(ErrorCodes.OK);

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.SHEDULE_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<SheduleDto>> searchShedule(HttpSession session, HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 @RequestParam(value = "groupId", required = false) String groupId,
                                                 @RequestParam(value = "disciplineId", required = false) String disciplineId,
                                                 @RequestParam(value = "year", required = false) String year,
                                                 @RequestParam(value = "userId", required = false) String userId) throws Exception {

        CommonResponse<List<SheduleDto>> commonResponse = new CommonResponse<List<SheduleDto>>();
        List<SheduleDto> dataList = new ArrayList<SheduleDto>();

        SheduleSearchBean sheduleSearchBean = new SheduleSearchBean();

        if (StringUtils.isNotEmpty(disciplineId)) {
            sheduleSearchBean.setDisciplineId(disciplineId);
        }

        if (StringUtils.isNotEmpty(userId)) {
            sheduleSearchBean.setUserId(userId);
        }

        if (StringUtils.isNotEmpty(groupId) && StringUtils.isNotEmpty(year)) {
            sheduleSearchBean.setStatus(Status.Active);
            sheduleSearchBean.setGroupId(groupId);
            sheduleSearchBean.setYear(year);

            try {
                dataList = sheduleService.search(sheduleSearchBean);
            } catch (BasicServiceException e) {
                log.debug(e.toString());
            }
        }

        commonResponse.setValue(dataList);

        return commonResponse;
    }
}
