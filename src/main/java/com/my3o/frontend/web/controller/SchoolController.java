package com.my3o.frontend.web.controller;

import com.my3o.backend.service.*;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.constant.Status;
import com.my3o.common.dto.*;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.*;
import com.my3o.frontend.constant.My3oURL;
import com.my3o.frontend.web.model.CommonResponse;
import com.my3o.frontend.web.model.ReferenceBookModel;
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
public class SchoolController extends AbstractFrontendController {

    @Autowired
    private IDisciplineService disciplineService;

    @Autowired
    private IJournalService journalService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IParentService parentService;

    @Autowired
    private ISheduleService sheduleService;

    @Autowired
    private IGroupService groupService;

    @RequestMapping(value = My3oURL.VISITS_LIST, method = RequestMethod.GET)
    public String visitsView(HttpServletRequest request, Model model) {

        return "visits";
    }

    @RequestMapping(value = My3oURL.SHEDULES_LIST, method = RequestMethod.GET)
    public String shedulesView(HttpServletRequest request, Model model) {

        return "shedules";
    }

    @RequestMapping(value = My3oURL.MARKS_LIST, method = RequestMethod.GET)
    public String marksView(HttpServletRequest request, Model model) {
        String userId = this.getUserId(request);
        String userType = this.getUserRole(request);
        List<UserDto> userDtoList = new ArrayList<UserDto>();
        String selectOrganization = this.cookieProvider.getOrganizationId(request);
        List<GroupDto> groupDtoList = new ArrayList<GroupDto>();
        List<SheduleDto> sheduleDtoList = new ArrayList<SheduleDto>();

        if (userType.equals("Parent")) {
            try {
                UserSearchBean userSearchBean = new UserSearchBean(userId);
                UserDto userDto = parentService.search(userSearchBean).get(0);
                userDtoList = userDto.getUserList();
            } catch (BasicServiceException e) {
                e.printStackTrace();
            }
        }

        if (!(userType.equals("Parent") || userType.equals("Scholar"))) {
            if (StringUtils.isNotBlank(selectOrganization) && !selectOrganization.equals("disabled")) {
                if (!userType.equals("Teacher")) {
                    GroupSearchBean groupSearchBean = new GroupSearchBean();
                    groupSearchBean.setOrganizationId(selectOrganization);
                    try {
                        groupDtoList = groupService.search(groupSearchBean);
                    } catch (BasicServiceException e) {
                        log.error(e.getMessage(), e);
                    }
                } else {
                    SheduleSearchBean sheduleSearchBean = new SheduleSearchBean();
                    sheduleSearchBean.setStatus(Status.Active);
                    sheduleSearchBean.setYear("2014");
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
        }

        model.addAttribute("userDtoList", userDtoList);
        model.addAttribute("groupList", groupDtoList);

        return "marks";
    }

    @RequestMapping(value = My3oURL.MARKS_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<Model> searchMarks(HttpSession session, HttpServletRequest request, Model model,
                                                   HttpServletResponse response,
                                                   @RequestParam(value = "groupId", required = false) String groupId,
                                                   @RequestParam(value = "userId", required = false) String userId,
                                                   @RequestParam(value = "date", required = false) String date,
                                                   @RequestParam(value = "numberDays", required = false) Integer numberDays,
                                                   @RequestParam(value = "disciplineId", required = false) String disciplineId) throws Exception {

        CommonResponse<Model> commonResponse = new CommonResponse<Model>();

        List<SheduleDto> sheduleDtoList = new ArrayList<SheduleDto>();

        try {
            UserDto userDto = new UserDto();
            UserSearchBean userSearchBean = new UserSearchBean(userId);
            userDto = userService.search(userSearchBean).get(0);

            SheduleSearchBean sheduleSearchBean = new SheduleSearchBean();
            sheduleSearchBean.setGroupId(userDto.getGroupList().get(0).getId());

            Integer year = Integer.valueOf(date.substring(6));
            if ((Integer.valueOf(date.substring(3, 5)) - 1) < 7) {
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

        if (StringUtils.isNotEmpty(userId)) {
            journalSearchBean.setUserId(userId);
        }

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
        model.addAttribute("sheduleDtoList", sheduleDtoList);

        commonResponse.setValue(model);

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.CLASS_LIST, method = RequestMethod.GET)
    public String classView(HttpServletRequest request, Model model) {

        return "class";
    }

    @RequestMapping(value = My3oURL.ROOMS_LIST, method = RequestMethod.GET)
    public String roomsView(HttpServletRequest request, Model model) {

        return "rooms";
    }

    @RequestMapping(value = My3oURL.PLANS_LIST, method = RequestMethod.GET)
    public String plansView(HttpServletRequest request, Model model) {

        return "plans";
    }

    @RequestMapping(value = My3oURL.DISCIPLINES_LIST, method = RequestMethod.GET)
    public String coursesView(HttpServletRequest request, Model model) {

        return "disciplines";
    }

    @RequestMapping(value = My3oURL.DISCIPLINES_EDIT, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<DisciplineDto> postCountry(HttpSession session, HttpServletRequest request,
                                           HttpServletResponse response, @RequestBody ReferenceBookModel dataModel) throws Exception {
        CommonResponse<DisciplineDto> commonResponse = new CommonResponse<DisciplineDto>();

        DisciplineDto disciplineDto = new DisciplineDto();
        if("-1".equals(dataModel.getId())){
            disciplineDto.setId(null);
        }else{
            disciplineDto.setId(dataModel.getId());
        }
        disciplineDto.setName(dataModel.getName());
        disciplineDto.setDescription(dataModel.getDescription());
        disciplineDto.setStatus(dataModel.getStatus());

        disciplineService.save(disciplineDto);

        return commonResponse;

    }

    @RequestMapping(value = My3oURL.DISCIPLINES_EDIT, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> deleteDisciplines(HttpSession session, HttpServletRequest request,
                                         HttpServletResponse response, @RequestBody ReferenceBookModel dataModel) throws Exception {

        disciplineService.delete(dataModel.getId());
        CommonResponse<String> commonResponse = new CommonResponse<String>();
        commonResponse.setErrorCode(ErrorCodes.OK);
        return commonResponse;

    }

    @RequestMapping(value = My3oURL.DISCIPLINES_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<DisciplineDto>> searchDisciplines(HttpSession session, HttpServletRequest request, Model model,
                                      HttpServletResponse response) throws Exception {

        CommonResponse<List<DisciplineDto>> commonResponse = new CommonResponse<List<DisciplineDto>>();

        List<DisciplineDto> disciplineDtoList = new ArrayList<DisciplineDto>();

        try {
            disciplineDtoList = disciplineService.search(new DisciplineSearchBean());
        } catch (BasicServiceException e) {
            e.printStackTrace();
        }

        commonResponse.setValue(disciplineDtoList);

        return commonResponse;
    }


}
