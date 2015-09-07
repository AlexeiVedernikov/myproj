package com.my3o.frontend.web.controller;

import com.my3o.backend.service.IGroupService;
import com.my3o.backend.service.IOrganizationService;
import com.my3o.backend.service.IParentService;
import com.my3o.backend.service.IUserService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.GroupDto;
import com.my3o.common.dto.OrganizationDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.GroupSearchBean;
import com.my3o.common.searchbean.UserSearchBean;
import com.my3o.frontend.constant.My3oURL;
import com.my3o.frontend.web.model.CommonResponse;
import com.my3o.frontend.web.model.GroupWebModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Eugene Tuysus 21 июля 2014 г.
 * 
 */
@Controller
public class GroupController extends AbstractFrontendController {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IParentService parentService;

    @RequestMapping(value = My3oURL.GROUP_LIST, method = RequestMethod.GET)
    public String getGroupView(HttpServletRequest request, Model model, HttpSession session) {

        try {
            String userType = this.getUserRole(request);
            String userId = this.getUserId(request);
            String selectOrganization = this.cookieProvider.getOrganizationId(request);
            List<GroupDto> groupDtoList = new ArrayList<GroupDto>();

            if (StringUtils.isNotBlank(selectOrganization) && !selectOrganization.equals("disabled")) {
                GroupSearchBean groupSearchBean = new GroupSearchBean();
                groupSearchBean.setOrganizationId(selectOrganization);
                try {
                    groupDtoList = groupService.search(groupSearchBean);
                } catch (BasicServiceException e) {
                    log.error(e.getMessage(), e);
                }

            } else if (userType.equals("SuperAdmin")) {
                groupDtoList = groupService.search(new GroupSearchBean());

            } else {
                UserSearchBean userSearchBean = new UserSearchBean();
                userSearchBean.addKey(userId);
                UserDto userDto = userService.search(userSearchBean).get(0);

                List<OrganizationDto> organizationDtoList = null;

                if (userType.equals("Parent")) {
                    try {
                        organizationDtoList = new ArrayList<OrganizationDto>(parentService.determineOrganizationParent(userId));
                    } catch (BasicServiceException e) {
                        e.printStackTrace();
                    }
                } else {
                    organizationDtoList = new ArrayList<OrganizationDto>(userDto.getOrganizationList());
                }

                GroupSearchBean groupSearchBean = new GroupSearchBean();

                for (OrganizationDto organizationDto : organizationDtoList) {
                    groupSearchBean.setOrganizationId(organizationDto.getId());
                    try {
                        groupDtoList.addAll(groupService.search(groupSearchBean));
                    } catch (BasicServiceException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }

            model.addAttribute("groupList", groupDtoList);

        } catch (BasicServiceException e) {
            log.error(e.getMessage(), e);
        }

        return "groups";
    }

    @RequestMapping(value = My3oURL.GROUP, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<GroupDto> postGroup(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            @RequestBody GroupWebModel dataModel) throws Exception {
        CommonResponse<GroupDto> commonResponse = new CommonResponse<GroupDto>();
        GroupDto groupDto = new GroupDto();

        if ("-1".equals(dataModel.getId())) {
            groupDto.setId(null);
        } else {
            groupDto.setId(dataModel.getId());
        }
        groupDto.setName(dataModel.getName());
        groupDto.setNumber(dataModel.getNumber());
        groupDto.setDescription(dataModel.getDescription());
        groupDto.setStatus(dataModel.getStatus());
        groupDto.setOrganizationDto(new OrganizationDto(dataModel.getOrganizationId()));

        GroupSearchBean gsb = new GroupSearchBean();
        GroupDto gDto = new GroupDto();
        gsb.addKey(dataModel.getId());

        try {
            gDto = groupService.search(gsb).get(0);
            groupDto.setCreateTime(gDto.getCreateTime());
            groupDto.setLastUpdateTime(Calendar.getInstance().getTime());
        } catch (BasicServiceException e) {
            groupDto.setCreateTime(Calendar.getInstance().getTime());
        }

        String loginUserId = null;
        loginUserId = this.getUserId(request);

        GroupSearchBean gsb1 = new GroupSearchBean();
        GroupDto groupCreateDto = new GroupDto();
        gsb1.addKey(dataModel.getId());
        try {
            groupCreateDto = groupService.search(gsb1).get(0);
            groupDto.setCreateByUserId(groupCreateDto.getCreateByUserId());
            groupDto.setUpdateByUserId(loginUserId);
        } catch (BasicServiceException e) {
            groupDto.setCreateByUserId(loginUserId);
        }

        commonResponse.setValue(groupService.save(groupDto));

        return commonResponse;

    }

    @RequestMapping(value = My3oURL.GROUP, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> deleteGroup(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            @RequestBody GroupWebModel dataModel) throws Exception {

        GroupDto groupDto = new GroupDto();

        if ("-1".equals(dataModel.getId())) {
            groupDto.setId(null);
        } else {
            groupDto.setId(dataModel.getId());
        }

        groupService.delete(dataModel.getId());
        CommonResponse<String> commonResponse = new CommonResponse<String>();
        commonResponse.setErrorCode(ErrorCodes.OK);
        return commonResponse;

    }

    @RequestMapping(value = My3oURL.GROUP_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<GroupDto>> searchGroup(HttpSession session, HttpServletRequest request,
                                               HttpServletResponse response,
                                               @RequestParam(value = "organizationIdList", required = false) String organizationId) throws Exception {

        CommonResponse<List<GroupDto>> commonResponse = new CommonResponse<List<GroupDto>>();

        GroupSearchBean searchBean = new GroupSearchBean();

        searchBean.setOrganizationId(organizationId);

        List<GroupDto> dataList = new ArrayList<GroupDto>();

        try {
            dataList = groupService.search(searchBean);
        } catch (BasicServiceException e) {
        }

        commonResponse.setValue(dataList);

        return commonResponse;
    }

}
