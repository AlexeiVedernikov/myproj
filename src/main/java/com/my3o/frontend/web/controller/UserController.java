package com.my3o.frontend.web.controller;

import com.my3o.backend.config.IBackendProperties;
import com.my3o.backend.service.*;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.constant.UserType;
import com.my3o.common.dto.GroupDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.GroupSearchBean;
import com.my3o.common.searchbean.UserSearchBean;
import com.my3o.frontend.constant.My3oURL;
import com.my3o.frontend.web.model.CommonResponse;
import com.my3o.frontend.web.model.ParentChildWebModel;
import com.my3o.frontend.web.model.UserWebModel;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by: andrew
 */
@Controller
public class UserController extends AbstractFrontendController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IParentService parentService;

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IBillUserService billUserService;

    @Autowired
    private IBackendProperties backendProperties;

    @RequestMapping(value = My3oURL.USER_LIST, method = RequestMethod.GET)
    public String userView(HttpServletRequest request, Model model, HttpSession session) {

//        try {
//            String userType = this.getUserRole(request);
//            String userId = this.getUserId(request);
        String selectOrganization = this.cookieProvider.getOrganizationId(request);

//            model.addAttribute("userList", userService.userView(userType, userId, selectOrganization));

        List<GroupDto> groupDtoList = new ArrayList<GroupDto>();

        if (StringUtils.isNotBlank(selectOrganization) && !selectOrganization.equals("disabled")) {
            GroupSearchBean groupSearchBean = new GroupSearchBean();
            groupSearchBean.setOrganizationId(selectOrganization);
            try {
                groupDtoList = groupService.search(groupSearchBean);
            } catch (BasicServiceException e) {
                log.error(e.getMessage(), e);
            }
        }

//        List<OrganizationDto> organizationDtoList = new ArrayList<OrganizationDto>();
//        OrganizationSearchBean organizationSearchBean = new OrganizationSearchBean();
//        organizationSearchBean.setStatus(Status.Active);
//        try {
//            organizationDtoList = organizationService.search(organizationSearchBean);
//        } catch (BasicServiceException e) {
//            log.error(e.getMessage(), e);
//        }

        model.addAttribute("groupList", groupDtoList);
        model.addAttribute("selectedOrganization", selectOrganization);
//        model.addAttribute("organizationDtoList", organizationDtoList);

//        } catch (BasicServiceException e) {
//            log.error(e.getMessage(), e);
//        }

        return "users";
    }

    @RequestMapping(value = My3oURL.USER, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<UserDto> postUser(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            @RequestBody UserWebModel dataModel) throws Exception {
        CommonResponse<UserDto> commonResponse = new CommonResponse<UserDto>();

        String loginUserId = null;
        loginUserId = this.getUserId(request);
        commonResponse.setValue(userService.register(dataModel, loginUserId));

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.USER, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> userDelete(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            @RequestBody UserWebModel dataModel) throws Exception {

        userService.delete(dataModel.getId());

        CommonResponse<String> commonResponse = new CommonResponse<String>();

        commonResponse.setErrorCode(ErrorCodes.OK);

        return commonResponse;

    }

    @RequestMapping(value = My3oURL.USER_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<UserDto>> searchUser(HttpSession session, HttpServletRequest request,
            HttpServletResponse response,   @RequestParam(value = "groupId", required = false) String groupId,
                                            @RequestParam(value = "userType", required = false) UserType userType,
                                            @RequestParam(value = "id", required = false) String userId) throws Exception {


        String selectOrganization = this.cookieProvider.getOrganizationId(request);
        CommonResponse<List<UserDto>> commonResponse = new CommonResponse<List<UserDto>>();

        List<UserDto> userDataList = new ArrayList<UserDto>();
        List<UserDto> parentDataList = new ArrayList<UserDto>();
        UserSearchBean userSearchBean = new UserSearchBean();
        UserSearchBean parentSearchBean = new UserSearchBean();

        if (userType != null) {
            userSearchBean.setUserType(userType);
        }

        if (!selectOrganization.equals("disabled") && userType != null && !(userType.equals(UserType.SuperAdmin)
                || userType.equals(UserType.RegistrarAccounts) || userType.equals(UserType.Onlooker))) {
            userSearchBean.setOrganizationId(selectOrganization);
        }

        if (StringUtils.isNotBlank(userId)) {
            userDataList.add(0, userService.search(new UserSearchBean(userId)).get(0));
        }

        if (StringUtils.isNotBlank(groupId)) {
            userSearchBean.setGroupId(groupId);
        }

        if (userType != null && userType.equals(UserType.Parent)) {
            try {
                userSearchBean.setUserType(UserType.Scholar);
                userDataList = userService.search(userSearchBean);
                for (UserDto dto : userDataList) {
                    parentSearchBean.setChildrenId(dto.getId());
                    //Сохраняет только последнего родителя, а надо всех
                    try {
                        parentDataList.addAll(userService.search(parentSearchBean));
                    } catch (BasicServiceException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            } catch (BasicServiceException e) {
                log.error(e.getMessage(), e);
            }
            Set<UserDto> set = new HashSet<UserDto>(parentDataList);
            parentDataList.clear();
            parentDataList.addAll(set);
            commonResponse.setValue(parentDataList);
        } else if (userType != null && userType.equals(UserType.SuperAdmin)) {
            userSearchBean.setUserType(UserType.SuperAdmin);
            try {
                userDataList = userService.search(userSearchBean);
            } catch (BasicServiceException e) {
                log.error(e.getMessage(), e);
            }
            commonResponse.setValue(userDataList);
        } else {
            try {
                userDataList = userService.search(userSearchBean);
            } catch (BasicServiceException e) {
                log.error(e.getMessage(), e);
            }
            commonResponse.setValue(userDataList);
        }

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.USER_PASSWORD, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<String> userPassword(HttpSession session, HttpServletRequest request,
                                               HttpServletResponse response, @RequestParam(value = "id", required = false) String userId) throws Exception {

        CommonResponse<String> commonResponse = new CommonResponse<>();

        UserSearchBean searchBean = new UserSearchBean(userId);

        String password = null;
        try {
            password = userService.search(searchBean).get(0).getPassword();
        } catch (BasicServiceException e) {
            for (int i = 0; i < 6; i++) {
                password = RandomStringUtils.randomAlphanumeric(6);
            }
//            e.printStackTrace();
        }

        commonResponse.setValue(password);

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.ADD_PARENT, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<UserDto> postParentAdd(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody ParentChildWebModel dataModel) throws Exception {

        CommonResponse<UserDto> commonResponse = new CommonResponse<UserDto>();

        try {
            commonResponse.setValue(parentService.parentAdd(request, response, dataModel));
        } catch (Exception e) {
            log.error("can't add parent");
        }

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.ADD_CHILD, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<UserDto> postChildAdd(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            @RequestBody ParentChildWebModel dataModel) throws Exception {

        CommonResponse<UserDto> commonResponse = new CommonResponse<UserDto>();

        try {
            commonResponse.setValue(parentService.childAdd(request, response, dataModel));
        } catch (Exception e) {
            log.error("can't add child");
        }

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.PARENTS_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<UserDto>> searchParent(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestParam(value = "name", required = false) String name) throws Exception {

        CommonResponse<List<UserDto>> commonResponse = new CommonResponse<List<UserDto>>();

        UserSearchBean searchBean = new UserSearchBean();

        searchBean.setUserType(UserType.Parent);
        searchBean.setName(name);
        List<UserDto> dataList = new ArrayList<UserDto>();

        if (StringUtils.isNotBlank(name)) {
            try {
                dataList = parentService.search(searchBean);
            } catch (BasicServiceException e) {
                e.printStackTrace();
            }
        }

        commonResponse.setValue(dataList);

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.CHILDREN_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<UserDto>> searchChild(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestParam(value = "name", required = false) String name) throws Exception {

        CommonResponse<List<UserDto>> commonResponse = new CommonResponse<List<UserDto>>();

        UserSearchBean searchBean = new UserSearchBean();

        searchBean.setUserType(UserType.Scholar);
        searchBean.setName(name);
        List<UserDto> dataList = new ArrayList<UserDto>();

        if (StringUtils.isNotBlank(name)) {
            try {
                dataList = parentService.search(searchBean);
            } catch (BasicServiceException e) {
                e.printStackTrace();
            }
        }

        commonResponse.setValue(dataList);

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.DROP_PARENT_OR_CHILD, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<UserDto> deleteChildAndParent(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody ParentChildWebModel dataModel) throws Exception {

        CommonResponse<UserDto> commonResponse = new CommonResponse<UserDto>();
        UserDto result = new UserDto();

        if (dataModel.getChildId() == null || dataModel.getParentId() == null) {
            return null;
        }

        try {
            UserSearchBean searchBeanStudent = new UserSearchBean();
            searchBeanStudent.addKey(dataModel.getChildId());
            UserSearchBean searchBeanParent = new UserSearchBean();
            searchBeanParent.addKey(dataModel.getParentId());

            UserDto studentDto = parentService.search(searchBeanStudent).get(0);
            UserDto parentDto = parentService.search(searchBeanParent).get(0);

            result = parentService.delete(studentDto, parentDto);

            commonResponse.setValue(result);
            return commonResponse;
        } catch (BasicServiceException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = My3oURL.USER_PROFILE, method = RequestMethod.GET)
    public String userProfileView(HttpServletRequest request, Model model,
            @RequestParam(value = "id", required = false) String idStr) {
        UserDto userDto = new UserDto();

        if (StringUtils.isNotBlank(idStr)) {
            try {
                UserSearchBean searchBean = new UserSearchBean();
                searchBean.addKey(idStr);
                UserDto a = parentService.search(searchBean).get(0);
                if (a != null) {
                    userDto = a;
                } else {
                    log.error("no emtity with such ID");
                }
            } catch (Exception e1) {
                log.error("can't get user search id");
            }
        }

        model.addAttribute("user", userDto);
        model.addAttribute("userList", userDto.getUserList());

        return "user-profile";
    }

//    @RequestMapping(value = My3oURL.PAID_GROUPS, method = RequestMethod.GET)
//    public String paidGroupsView(HttpServletRequest request, Model model, HttpSession session) {
//
//        String selectOrganization = this.cookieProvider.getOrganizationId(request);
//        List<GroupDto> groupDtoList = new ArrayList<GroupDto>();
//        Map<GroupDto, Integer> paidGroupMap = new LinkedHashMap<>();
////        Map<GroupDto, Integer> paidGroupMapAll = new LinkedHashMap<>();
//        List<UserDto> userDataList = new ArrayList<UserDto>();
//        List<UserDto> parentDataList = new ArrayList<UserDto>();
//        UserSearchBean userSearchBean = new UserSearchBean();
//        UserSearchBean parentSearchBean = new UserSearchBean();
//        BillUserSearchBean billUserSearchBean = new BillUserSearchBean();
//        List<BillUserDto> billUserDtoList = new ArrayList<>();
//
//        Integer a = 0;
//
//
//        if (StringUtils.isNotBlank(selectOrganization) && !selectOrganization.equals("disabled")) {
//            GroupSearchBean groupSearchBean = new GroupSearchBean();
//            groupSearchBean.setOrganizationId(selectOrganization);
//            try {
//                groupDtoList = groupService.search(groupSearchBean);
//            } catch (BasicServiceException e) {
//                log.error(e.getMessage(), e);
//            }
//        }
//
//        for (int i = 0; i < groupDtoList.size(); i++){
//            a = 0;
////            for test without base
////            if(i == 0){
////                a = 0;
////            }
////            else if(i == 1){
////                a = 5;
////            }
////            else if(i == 2){
////                a = 11;
////            }
//            try {
//                userSearchBean.setUserType(UserType.Scholar);
//                userSearchBean.setOrganizationId(selectOrganization);
//                userSearchBean.setGroupId(groupDtoList.get(i).getId());
////                try {
//                    userDataList = userService.search(userSearchBean);
//
//                for (UserDto dto : userDataList) {
//                    parentSearchBean.setChildrenId(dto.getId());
////                    try {
//                        parentDataList.addAll(userService.search(parentSearchBean));
////                    } catch (BasicServiceException e) {
////                        log.error(e.getMessage(), e);
////                    }
//                }
//                for(int j = 0; j < parentDataList.size(); j++){
//                    billUserSearchBean.setUserId(parentDataList.get(j).getId());
//                    billUserDtoList = billUserService.search(billUserSearchBean);
//                }
//                for(int k = 0; k < billUserDtoList.size(); k++){
//                    if(billUserDtoList.get(k).getQiwiStatus().equals(QiwiStatus.paid)){
//                        a++;
//                    }
//                }
////                } catch (BasicServiceException e) {
////                    log.error(e.getMessage(), e);
////                }
//
//
//            } catch (BasicServiceException e) {
//                log.error(e.getMessage(), e);
//            }
//            paidGroupMap.put(groupDtoList.get(i), a);
////            paidGroupMapAll.putAll(paidGroupMap);
//        }
//        model.addAttribute("paidGroupMap", paidGroupMap);
//
//        return "paidGroups";
//    }

    @RequestMapping(value = My3oURL.PAID_GROUPS, method = RequestMethod.GET)
    public String paidGroupsView(HttpServletRequest request, Model model, HttpSession session) {

        String selectOrganization = this.cookieProvider.getOrganizationId(request);
        List<GroupDto> groupDtoList = new ArrayList<GroupDto>();
        Map<GroupDto, Integer> paidGroupMap = new LinkedHashMap<>();
        List<UserDto> userDataList = new ArrayList<UserDto>();
        List<UserDto> parentDataList = new ArrayList<UserDto>();
        UserSearchBean userSearchBean = new UserSearchBean();
        UserSearchBean parentSearchBean = new UserSearchBean();

        Integer size = 0;


        if (StringUtils.isNotBlank(selectOrganization) && !selectOrganization.equals("disabled")) {
            GroupSearchBean groupSearchBean = new GroupSearchBean();
            groupSearchBean.setOrganizationId(selectOrganization);
            try {
                groupDtoList = groupService.search(groupSearchBean);
            } catch (BasicServiceException e) {
                log.error(e.getMessage(), e);
            }
        }

        for (int i = 0; i < groupDtoList.size(); i++){
            size = 0;
            try {
                userSearchBean.setUserType(UserType.Scholar);
                userSearchBean.setOrganizationId(selectOrganization);
                userSearchBean.setGroupId(groupDtoList.get(i).getId());
                userDataList = userService.search(userSearchBean);
                size = userDataList.size();
            } catch (BasicServiceException e) {
                log.error(e.getMessage(), e);
            }
            paidGroupMap.put(groupDtoList.get(i), size);
        }
        model.addAttribute("paidGroupMap", paidGroupMap);

        return "paidGroups";
    }
}
