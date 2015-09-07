package com.my3o.frontend.web.controller;

import com.my3o.backend.service.IGroupService;
import com.my3o.backend.service.IJournalService;
import com.my3o.backend.service.ISheduleService;
import com.my3o.backend.service.IUserService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.constant.Status;
import com.my3o.common.constant.UserType;
import com.my3o.common.dto.GroupDto;
import com.my3o.common.dto.JournalDto;
import com.my3o.common.dto.SheduleDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.GroupSearchBean;
import com.my3o.common.searchbean.JournalSearchBean;
import com.my3o.common.searchbean.SheduleSearchBean;
import com.my3o.common.searchbean.UserSearchBean;
import com.my3o.frontend.constant.My3oURL;
import com.my3o.frontend.web.model.CommonResponse;
import com.my3o.frontend.web.model.JournalWebModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class JournalController extends AbstractFrontendController {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IJournalService journalService;

    @Autowired
    private ISheduleService sheduleService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = My3oURL.JOURNAL_LIST, method = RequestMethod.GET)
    public String journalView(HttpServletRequest request, Model model) {

        String selectOrganization = this.cookieProvider.getOrganizationId(request);

        List<GroupDto> groupDtoList = new ArrayList<GroupDto>();
        List<SheduleDto> sheduleDtoList = new ArrayList<SheduleDto>();

        if (StringUtils.isNotBlank(selectOrganization) && !selectOrganization.equals("disabled")) {
            String userId = this.getUserId(request);
            String userType = this.getUserRole(request);

            if (!userType.equals("Teacher")) {
                GroupSearchBean groupSearchBean = new GroupSearchBean();
                groupSearchBean.setOrganizationId(selectOrganization);
                try {
                    groupDtoList = groupService.search(groupSearchBean);
                } catch (BasicServiceException e) {
                    log.error(e.getMessage(), e);
                }
            } else {
                Date date = new Date();
                Integer year = date.getYear() + 1900;
                if (date.getMonth() < 7) {
                    year = year - 1;
                }

                SheduleSearchBean sheduleSearchBean = new SheduleSearchBean();
                sheduleSearchBean.setStatus(Status.Active);
                sheduleSearchBean.setYear(String.valueOf(year));
                sheduleSearchBean.setUserId(userId);
                try {
                    sheduleDtoList = sheduleService.search(sheduleSearchBean);
                    for (SheduleDto sheduleDto : sheduleDtoList) {
                        groupDtoList.add(sheduleDto.getGroupDto());
                    }
                } catch (BasicServiceException e) {
                    log.debug(e.toString());
                }
            }
        }

        model.addAttribute("groupList", groupDtoList);

        return "journals";
    }

    @RequestMapping(value = My3oURL.JOURNAL, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<JournalDto> postJournal(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                     @RequestBody JournalWebModel dataModel) throws Exception {
        CommonResponse<JournalDto> commonResponse = new CommonResponse<JournalDto>();

        commonResponse.setValue(journalService.addAndEdit(dataModel));

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.JOURNAL, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> deleteJournal(HttpServletRequest request,
                                          HttpServletResponse response, @RequestBody JournalWebModel dataModel) throws Exception {

        journalService.delete(dataModel.getId());

        CommonResponse<String> commonResponse = new CommonResponse<String>();

        commonResponse.setErrorCode(ErrorCodes.OK);

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.JOURNAL_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<Model> searchJournal(HttpSession session, HttpServletRequest request, Model model,
                                                 HttpServletResponse response,
                                                 @RequestParam(value = "groupId", required = false) String groupId,
                                                 @RequestParam(value = "numberDays", required = false) Integer numberDays,
                                                 @RequestParam(value = "date", required = false) String date,
                                                 @RequestParam(value = "sheduleId", required = false) String sheduleId) throws Exception {

        CommonResponse<Model> commonResponse = new CommonResponse<Model>();

        List<UserDto> userDtoList = new ArrayList<UserDto>();
        UserSearchBean userSearchBean = new UserSearchBean();
        userSearchBean.setUserType(UserType.Scholar);
        userSearchBean.setGroupId(groupId);

        try {
            userDtoList = userService.search(userSearchBean);
        } catch (BasicServiceException e) {
            e.printStackTrace();
        }

        model.addAttribute("userDtoList", userDtoList);

        List<JournalDto> journalDtoList = new ArrayList<JournalDto>();
        JournalSearchBean journalSearchBean = new JournalSearchBean();
        journalSearchBean.setStatus(Status.Active);
        journalSearchBean.addSheduleId(sheduleId);

        if (StringUtils.isNotBlank(date)) {
            Date dateDate = new Date();
            dateDate.setDate(Integer.valueOf(date.substring(0, 2)));
            dateDate.setMonth(Integer.valueOf(date.substring(3, 5)) - 1);
            dateDate.setYear(Integer.valueOf(date.substring(6)) - 1900);
            journalSearchBean.addDate(date);
            for (int i=0; i<numberDays-1; i++) {
                dateDate.setDate(dateDate.getDate() + 1);
                String currentDay = String.valueOf(dateDate.getDate());
                if (dateDate.getDate() < 10) {
                    currentDay = "0" + currentDay;
                }
                String currentMonth = String.valueOf(dateDate.getMonth()+1);
                if (dateDate.getMonth() < 9) {
                    currentMonth = "0" + currentMonth;
                }
                journalSearchBean.addDate(currentDay + "-" + currentMonth + "-" + String.valueOf(dateDate.getYear()+1900));
            }
        }

        try {
            journalDtoList = journalService.search(journalSearchBean);
        } catch (BasicServiceException e) {
            e.printStackTrace();
        }

        model.addAttribute("journalDtoList", journalDtoList);

        commonResponse.setValue(model);

        return commonResponse;
    }
}
