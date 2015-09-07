package com.my3o.frontend.web.controller;

import com.my3o.backend.service.*;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.*;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.*;
import com.my3o.frontend.constant.My3oURL;
import com.my3o.frontend.web.model.*;
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

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
@Controller
public class ReferenceBookController extends AbstractFrontendController {

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
    private IOrganizationService organizationService;

    /**
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = My3oURL.COUNTRY_LIST, method = RequestMethod.GET)
    public String getCountriesView(HttpServletRequest request, Model model) {

        try {
            model.addAttribute("countryList", countryService.search(new CountrySearchBean()));
        } catch (BasicServiceException e) {
            // TODO Auto-generated catch block
            log.debug(e.toString());
        }
        return "countries";
    }

    /**
     * @param session
     * @param request
     * @param response
     * @param dataModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = My3oURL.COUNTRY, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<CountryDto> postCountry(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody ReferenceBookModel dataModel) throws Exception {
        CommonResponse<CountryDto> commonResponse = new CommonResponse<CountryDto>();
        CountryDto countryDto = new CountryDto();

        if ("-1".equals(dataModel.getId())) {
            countryDto.setId(null);
        } else {
            countryDto.setId(dataModel.getId());
        }
        countryDto.setName(dataModel.getName());
        countryDto.setDescription(dataModel.getDescription());
        countryDto.setStatus(dataModel.getStatus());

        commonResponse.setValue(countryService.save(countryDto));

        return commonResponse;

    }

    /**
     * @param session
     * @param request
     * @param response
     * @param dataModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = My3oURL.COUNTRY, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> deleteCountry(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody ReferenceBookModel dataModel) throws Exception {

        CountryDto countryDto = new CountryDto();

        if ("-1".equals(dataModel.getId())) {
            countryDto.setId(null);
        } else {
            countryDto.setId(dataModel.getId());
        }

        countryService.delete(dataModel.getId());
        CommonResponse<String> commonResponse = new CommonResponse<String>();
        commonResponse.setErrorCode(ErrorCodes.OK);
        return commonResponse;

    }

    @RequestMapping(value = My3oURL.REGION_LIST, method = RequestMethod.GET)
    public String getRegionView(HttpServletRequest request, Model model) {

        try {
            model.addAttribute("countryList", countryService.search(new CountrySearchBean()));
            model.addAttribute("regionList", regionService.search(new RegionSearchBean()));

        } catch (BasicServiceException e) {
            log.error(e.getMessage(), e);
        }

        return "regions";
    }

    @RequestMapping(value = My3oURL.REGION, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<RegionDto> postRegion(HttpSession session, HttpServletRequest request,
                                            HttpServletResponse response, @RequestBody RegionWebModel dataModel) throws Exception {

        CommonResponse<RegionDto> commonResponse = new CommonResponse<RegionDto>();

        RegionDto regionDto = new RegionDto();

        if ("-1".equals(dataModel.getId())) {
            regionDto.setId(null);
        } else {
            regionDto.setId(dataModel.getId());
        }

        regionDto.setName(dataModel.getName());
        regionDto.setDescription(dataModel.getDescription());
        regionDto.setStatus(dataModel.getStatus());
        regionDto.setCountryDto(new CountryDto(dataModel.getCountryId()));

        commonResponse.setValue(regionService.save(regionDto));

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.REGION, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> deleteRegion(HttpSession session, HttpServletRequest request,
                                            HttpServletResponse response, @RequestBody RegionWebModel dataModel) throws Exception {

        regionService.delete(dataModel.getId());

        CommonResponse<String> commonResponse = new CommonResponse<String>();

        commonResponse.setErrorCode(ErrorCodes.OK);

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.REGION_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<RegionDto>> searchRegion(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                  @RequestParam(value = "countryId", required = false) String countryId) throws Exception {
        CommonResponse<List<RegionDto>> commonResponse = new CommonResponse<List<RegionDto>>();

        RegionSearchBean searchBean = new RegionSearchBean();
        searchBean.setCountryId(countryId);
        List<RegionDto> dataList = new ArrayList<RegionDto>();

        try {
            dataList = regionService.search(searchBean);
        } catch (BasicServiceException e) {}

        commonResponse.setValue(dataList);

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.TOWN_LIST, method = RequestMethod.GET)
    public String getTownView(HttpServletRequest request, Model model) {

        try {
            model.addAttribute("countryList", countryService.search(new CountrySearchBean()));
            model.addAttribute("townList", townService.search(new TownSearchBean()));

        } catch (BasicServiceException e) {
            log.debug(e.toString());
        }

        return "town";
    }

    @RequestMapping(value = My3oURL.TOWN, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<TownDto> postTown(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            @RequestBody TownDataModel dataModel) throws Exception {
        CommonResponse<TownDto> commonResponse = new CommonResponse<TownDto>();

        TownDto townDto = new TownDto();

        if ("-1".equals(dataModel.getId())) {
            townDto.setId(null);
        } else {
            townDto.setId(dataModel.getId());
        }

        townDto.setName(dataModel.getName());
        townDto.setDescription(dataModel.getDescription());
        townDto.setStatus(dataModel.getStatus());

        townDto.setRegionDto(new RegionDto(dataModel.getRegionId()));

        townDto.setDistrictDto(new DistrictDto(dataModel.getDistrictId()));

        commonResponse.setValue(townService.save(townDto));

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.TOWN, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> deleteTown(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            @RequestBody TownDataModel dataModel) throws Exception {

        townService.delete(dataModel.getId());

        CommonResponse<String> commonResponse = new CommonResponse<String>();
        commonResponse.setErrorCode(ErrorCodes.OK);
        return commonResponse;
    }

    @RequestMapping(value = My3oURL.TOWN_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<TownDto>> searchTown(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam(value = "regionId", required = false) String regionId,
                                             @RequestParam(value = "districtId", required = false) String districtId) throws Exception {
        CommonResponse<List<TownDto>> commonResponse = new CommonResponse<List<TownDto>>();

        TownSearchBean searchBean = new TownSearchBean();
        List<TownDto> dataList = new ArrayList<TownDto>();
        List<TownDto> dataListResult = new ArrayList<TownDto>();
        searchBean.setRegionId(regionId);
        searchBean.setDistrictId(districtId);

        try {
            dataList = townService.search(searchBean);
        } catch (BasicServiceException e) {}

        if (searchBean.getRegionId() != null) {
            for (TownDto dto : dataList) {
                if (dto.getDistrictDto() == null) {
                    dataListResult.add(dto);
                }
            }
            dataList = dataListResult;
        }

        commonResponse.setValue(dataList);

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.DISTRICT_LIST, method = RequestMethod.GET)
    public String getDistrictView(HttpServletRequest request, Model model) {

        try {
            model.addAttribute("countryList", countryService.search(new CountrySearchBean()));
            model.addAttribute("districtList", districtService.search(new DistrictSearchBean()));

        } catch (BasicServiceException e) {
            log.error(e.getMessage(), e);
        }

        return "districts";
    }

    @RequestMapping(value = My3oURL.DISTRICT, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<DistrictDto> postDistrict(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody DistrictWebModel dataModel) throws Exception {

        CommonResponse<DistrictDto> commonResponse = new CommonResponse<DistrictDto>();

        DistrictDto districtDto = new DistrictDto();

        if ("-1".equals(dataModel.getId())) {
            districtDto.setId(null);
        } else {
            districtDto.setId(dataModel.getId());
        }

        districtDto.setName(dataModel.getName());
        districtDto.setDescription(dataModel.getDescription());
        districtDto.setStatus(dataModel.getStatus());
        districtDto.setRegionDto(new RegionDto(dataModel.getRegionId()));

        commonResponse.setValue(districtService.save(districtDto));

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.DISTRICT, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> deleteDistrict(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody DistrictWebModel dataModel) throws Exception {

        districtService.delete(dataModel.getId());

        CommonResponse<String> commonResponse = new CommonResponse<String>();

        commonResponse.setErrorCode(ErrorCodes.OK);

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.DISTRICT_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<DistrictDto>> searchDistrict(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                                     @RequestParam(value = "regionId", required = false) String regionId) throws Exception {
        CommonResponse<List<DistrictDto>> commonResponse = new CommonResponse<List<DistrictDto>>();

        DistrictSearchBean searchBean = new DistrictSearchBean();
        searchBean.setRegionId(regionId);
        List<DistrictDto> dataList = new ArrayList<DistrictDto>();

        try {
            dataList = districtService.search(searchBean);
        } catch (BasicServiceException e) {}

        commonResponse.setValue(dataList);

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.TOWN_AREA_LIST, method = RequestMethod.GET)
    public String getTownAreaView(HttpServletRequest request, Model model) {

        try {
            model.addAttribute("countryList", countryService.search(new CountrySearchBean()));
            model.addAttribute("townAreaList", townAreaService.search(new TownAreaSearchBean()));
        } catch (BasicServiceException e) {
            log.debug(e.toString());
        }

        return "townArea";
    }

    @RequestMapping(value = My3oURL.TOWN_AREA, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<TownAreaDto> postTownArea(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody TownAreaWebModel dataModel) throws Exception {

        CommonResponse<TownAreaDto> commonResponse = new CommonResponse<TownAreaDto>();
        TownAreaDto townAreaDto = new TownAreaDto();

        if ("-1".equals(dataModel.getId())) {
            townAreaDto.setId(null);
        } else {
            townAreaDto.setId(dataModel.getId());
        }

        townAreaDto.setName(dataModel.getName());
        townAreaDto.setDescription(dataModel.getDescription());
        townAreaDto.setStatus(dataModel.getStatus());
        townAreaDto.setTownDto(new TownDto(dataModel.getTownId()));

        commonResponse.setValue(townAreaService.save(townAreaDto));

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.TOWN_AREA, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> deleteTownArea(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @RequestBody TownAreaWebModel dataModel) throws Exception {

        CommonResponse<String> commonResponse = new CommonResponse<String>();
        TownAreaDto townAreaDto = new TownAreaDto();
        if ("-1".equals(dataModel.getId())) {
            townAreaDto.setId(null);
        } else {
            townAreaDto.setId(dataModel.getId());
        }

        townAreaService.delete(dataModel.getId());
        commonResponse.setErrorCode(ErrorCodes.OK);

        return commonResponse;

    }

    @RequestMapping(value = My3oURL.TOWN_AREA_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<TownAreaDto>> searchTownArea(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                                     @RequestParam(value = "townId", required = false) String townId) throws Exception {
        CommonResponse<List<TownAreaDto>> commonResponse = new CommonResponse<List<TownAreaDto>>();

        TownAreaSearchBean searchBean = new TownAreaSearchBean();
        searchBean.setTownId(townId);
        List<TownAreaDto> dataList = new ArrayList<TownAreaDto>();

        try {
            if (StringUtils.isNotBlank(townId)) {
                dataList = townAreaService.search(searchBean);
            }
        } catch (BasicServiceException e) {}

        commonResponse.setValue(dataList);

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.SCHOOLS_LIST, method = RequestMethod.GET)
    public String getSchoolsView(HttpServletRequest request, Model model) {

        return "schools";
    }

}
