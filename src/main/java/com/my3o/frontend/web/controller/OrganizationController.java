package com.my3o.frontend.web.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my3o.backend.service.*;
import com.my3o.common.dto.*;
import com.my3o.common.searchbean.*;
import com.my3o.frontend.constant.CommonWebConstant;
import com.my3o.frontend.constant.My3oURL;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.frontend.web.model.CommonResponse;
import com.my3o.frontend.web.model.OrganizationWebModel;

/**
 * @author anton
 * 
 */
@Controller
public class OrganizationController extends AbstractFrontendController {
    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private ITownService townService;

    @Autowired
    private ICountryService countryService;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IDistrictService districtService;

    @Autowired
    private ITownAreaService townAreaService;

    @Autowired
    private ILocationService locationService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = My3oURL.ORGANIZATION_LIST, method = RequestMethod.GET)
    public String getOrganizationView(HttpServletRequest request, Model model, HttpSession session) {

        try {
            String userType = this.getUserRole(request);
            String userId = this.getUserId(request);
            String selectOrganization = this.cookieProvider.getOrganizationId(request);
            List<OrganizationDto> organizationDtoList = organizationService.search(new OrganizationSearchBean());

            if (userType.equals("SuperAdmin") || userType.equals("RegistrarAccounts")) {
                model.addAttribute("organizationList", organizationDtoList);

            } else if (StringUtils.isNotBlank(selectOrganization) && !selectOrganization.equals("disabled")) {
                OrganizationSearchBean organizationSearchBean = new OrganizationSearchBean();
                organizationSearchBean.addKey(selectOrganization);
                organizationDtoList = organizationService.search(organizationSearchBean);

                model.addAttribute("organizationList", organizationDtoList);

            } else {
                UserSearchBean searchBean = new UserSearchBean();
                searchBean.addKey(userId);
                UserDto userDto = userService.search(searchBean).get(0);
                organizationDtoList = userDto.getOrganizationList();

                model.addAttribute("organizationList", organizationDtoList);
            }

        } catch (BasicServiceException e) {
            log.error(e.getMessage(), e);
        }

        return "organization";
    }

    @RequestMapping(value = My3oURL.ORGANIZATION, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<OrganizationDto> postOrganization(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody OrganizationWebModel dataModel) throws Exception {
        CommonResponse<OrganizationDto> commonResponse = new CommonResponse<OrganizationDto>();

        OrganizationDto organizationDto = new OrganizationDto();

        dataModel.getId();
        if ("".equals(dataModel.getId())) {
            organizationDto.setId(null);
        } else {
            organizationDto.setId(dataModel.getId());
        }

        organizationDto.setName(dataModel.getName());
        organizationDto.setDescription(dataModel.getDescription());
        organizationDto.setStatus(dataModel.getStatus());
        organizationDto.setPhone(dataModel.getPhone());
        organizationDto.setAddress(dataModel.getAddress());

        LocationDto locationDto = new LocationDto();
        locationDto.setCountryDto(countryService.search(new CountrySearchBean(dataModel.getCountryId())).get(0));
        locationDto.setRegionDto(regionService.search(new RegionSearchBean(dataModel.getRegionId())).get(0));

        LocationSearchBean lSearchBean = new LocationSearchBean();
        lSearchBean.setCountryId(dataModel.getCountryId());
        lSearchBean.setRegionId(dataModel.getRegionId());

        if (!dataModel.getDistrictId().equals("disabled")) {
            locationDto.setDistrictDto(districtService.search(new DistrictSearchBean(dataModel.getDistrictId())).get(0));
            lSearchBean.setDistrictId(dataModel.getDistrictId());
        }
        if (!dataModel.getTownId().equals("disabled")) {
            locationDto.setTownDto(townService.search(new TownSearchBean(dataModel.getTownId())).get(0));
            lSearchBean.setTownId(dataModel.getTownId());
        }
        if (!dataModel.getTownAreaId().equals("disabled")) {
            locationDto.setTownAreaDto(townAreaService.search(new TownAreaSearchBean(dataModel.getTownAreaId())).get(0));
            lSearchBean.setTownAreaId(dataModel.getTownAreaId());
        }

        try {
            for (LocationDto dto : locationService.search(lSearchBean)) {
                if (dto.equals(locationDto)) {
                    locationDto = dto;
                    break;
                }
            }
        } catch (BasicServiceException e) {
            locationDto = locationService.save(locationDto);
        }

        organizationDto.setLocationDto(locationDto);

        commonResponse.setValue(organizationService.save(organizationDto));
        commonResponse.setUrl(response.encodeRedirectURL(request.getContextPath()) + "/organizations");

        if (commonResponse.getValue() != null) {
            Map<String, String> organizationList = new TreeMap<String, String>();
            for (OrganizationDto organizationDtoL : organizationService.search(new OrganizationSearchBean())) {
                organizationList.put(organizationDtoL.getId(), organizationDtoL.getName());
            }
//            session.setAttribute(CommonWebConstant.listOrg.name(), organizationList);
        }

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.ORGANIZATION_VIEW, method = RequestMethod.GET)
    public String organizationEditView(HttpServletRequest request, Model model,
            @RequestParam(value = "id", required = false) String idStr) {
        OrganizationDto result = new OrganizationDto();

        long start = System.currentTimeMillis();

        if (StringUtils.isNotBlank(idStr)) {
            try {
                OrganizationSearchBean searchBean = new OrganizationSearchBean();
                searchBean.addKey(idStr);
                List<OrganizationDto> a = organizationService.search(searchBean);
                if (CollectionUtils.isNotEmpty(a)) {
                    result = a.get(0);
                } else {
                    log.error("no emtity with such ID");
                }
            } catch (Exception e1) {
                log.error("can't get organization search id");
            }

        }
        // CountrySearchBean()));
        // Fill lists of possible information
        try {
            model.addAttribute("countryList", countryService.search(new CountrySearchBean()));
            model.addAttribute("locationList", locationService.search(new LocationSearchBean()));
            model.addAttribute("organizationList", organizationService.search(new OrganizationSearchBean()));
        } catch (BasicServiceException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }
        model.addAttribute("test", result);

        long end = System.currentTimeMillis();
        long traceTime = end - start;

        return "dialog/organization-edit";
    }

    @RequestMapping(value = My3oURL.ORGANIZATION, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> deleteOrganization(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody OrganizationWebModel dataModel) throws Exception {

        organizationService.delete(dataModel.getId());

        CommonResponse<String> commonResponse = new CommonResponse<String>();
        commonResponse.setErrorCode(ErrorCodes.OK);
        return commonResponse;
    }

    @RequestMapping(value = My3oURL.ORGANIZATION_SELECT, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<String> postOrganizationSelect(HttpSession session, HttpServletRequest request,
                                                     HttpServletResponse response, @RequestBody OrganizationWebModel dataModel) throws Exception {
        CommonResponse<String> commonResponse = new CommonResponse<String>();

        cookieProvider.setOrganizationId(request, response, dataModel.getId());

        return commonResponse;
    }
}
