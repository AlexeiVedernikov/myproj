package com.my3o.frontend.web.interceptor;

import com.my3o.backend.service.IBillUserService;
import com.my3o.backend.service.IOrganizationService;
import com.my3o.backend.service.IUserService;
import com.my3o.common.constant.QiwiStatus;
import com.my3o.common.constant.UserType;
import com.my3o.common.dto.BillUserDto;
import com.my3o.common.dto.OrganizationDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.BillUserSearchBean;
import com.my3o.common.searchbean.OrganizationSearchBean;
import com.my3o.common.searchbean.UserSearchBean;
import com.my3o.frontend.config.IFrontendProperties;
import com.my3o.frontend.web.model.My3oAuthCookie;
import com.my3o.frontend.web.security.My3oCookieProvider;
import com.my3o.frontend.web.security.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: Alexander Duckardt
 * Date: 7/28/14.
 */
public class SecurityInterceptor  extends HandlerInterceptorAdapter {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private My3oCookieProvider cookieProvider;
    @Autowired
    private IFrontendProperties frontendProperties;

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IBillUserService billUserService;

    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        log.info("================ Security interceptor Handler =======================");
        final String userId = cookieProvider.getUserId(request);
        final String roleId = cookieProvider.getUserRole(request);
        final My3oAuthCookie newCookie = cookieProvider.renew(request, response);
        if (!SecurityUtils.isURLOpen(request)){
            if(cookieProvider.cookieRenewFailedForThisRequest(request)) {
                cookieProvider.invalidate(request, response);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            } else if(newCookie==null){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            if(!SecurityUtils.isURLAllowed(UserType.valueOf(roleId),request)){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }

        if (StringUtils.isNotBlank(userId)) {

            if (roleId.equals("Scholar") || roleId.equals("Parent")) {
                BillUserSearchBean billUserSearchBean = new BillUserSearchBean();
                billUserSearchBean.setUserId(userId);
                try {
                    List<BillUserDto> billUserDtoList = billUserService.search(billUserSearchBean);
                    for (BillUserDto billUserDto : billUserDtoList) {
                        if (!(billUserDto.getQiwiStatus() == QiwiStatus.paid || billUserDto.getQiwiStatus() == QiwiStatus.waiting ||
                                billUserDto.getQiwiStatus() == QiwiStatus.New)) {
                            if (blockURL(request.getRequestURI())) {
                                response.sendRedirect("/my3o/block");
                                break;
                            }
                        }
                    }
                } catch (BasicServiceException e) {
                    e.printStackTrace();
                }
            }

            List<OrganizationDto> organizationDtoList = null;
            UserSearchBean userSearchBean = new UserSearchBean();
            userSearchBean.addKey(userId);
            UserDto userDto = null;
            try {
                userDto = userService.search(userSearchBean).get(0);
            } catch (BasicServiceException e) {
                e.printStackTrace();
            }
            if (roleId.equals("SuperAdmin") || roleId.equals("RegistrarAccounts") || roleId.equals("Onlooker")) {
                try {
                    organizationDtoList = organizationService.search(new OrganizationSearchBean());
                } catch (BasicServiceException e) {
                    log.error(e.getMessage(), e);
                }
            } else {
                organizationDtoList = userDto.getOrganizationList();
            }
            Map<String, String> organizationList = new LinkedHashMap<>();
            if (organizationDtoList != null) {
                for (OrganizationDto organizationDtoL : organizationDtoList) {
                    organizationList.put(organizationDtoL.getId(), organizationDtoL.getName());
                }
            }

            request.setAttribute("listOrg", organizationList);
            request.setAttribute("userName", userDto.getName());
            request.setAttribute("userId", userId);

            String selectOrganization = this.cookieProvider.getOrganizationId(request);
            if (!roleId.equals("Parent")) {
                if (!(StringUtils.isNotBlank(selectOrganization) && !selectOrganization.equals("disabled"))) {
                    cookieProvider.setOrganizationId(request, response, organizationDtoList.get(0).getId());
                }
            }
        }
        return true;
    }

    public boolean blockURL(String URL) {
        List<String> availableURL = new ArrayList<String>();
        availableURL.add("/my3o/");
        availableURL.add("/my3o/bill-user");
        availableURL.add("/my3o/logout");
        availableURL.add("/my3o/block");
        availableURL.add("/my3o/terms");
        availableURL.add("/my3o/qiwiInfo");
        availableURL.add("/my3o/contacts");
        availableURL.add("/my3o/user-profile");

        for (String s : availableURL) {
            if (s.equals(URL)) {
                return false;
            }
        }
        return true;
    }

}
