package com.my3o.frontend.web.controller;

import com.my3o.backend.service.IAuthenticationService;
import com.my3o.backend.service.IOrganizationService;
import com.my3o.backend.service.IUserService;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.dto.AuthenticationResponse;
import com.my3o.common.dto.UserDto;
import com.my3o.frontend.constant.My3oURL;
import com.my3o.frontend.web.model.CommonResponse;
import com.my3o.frontend.web.model.LoginModel;
import com.my3o.frontend.web.security.My3oCookieProvider;
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

/**
 * @author anton
 * 
 */
@Controller
public class LoginController extends AbstractFrontendController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private IAuthenticationService authenticationService;
    @Autowired
    private My3oCookieProvider cookieProvider;


    @RequestMapping(value = My3oURL.LOGIN, method = RequestMethod.GET)
    public String userView(HttpServletRequest request, Model model) {

        return "login-page";
    }

    @RequestMapping(value = My3oURL.LOGIN, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<String> processLogin(HttpSession session, @RequestBody LoginModel loginModel,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommonResponse<String> commonResponse = new CommonResponse<String>();

        String loginRes = null;
        UserDto resStatus = new UserDto();

        if (!loginModel.getEmail().isEmpty() || !loginModel.getPassword().isEmpty()) {

            AuthenticationResponse authenticationResponse = authenticationService.login(loginModel.getEmail(), loginModel.getPassword());
            if(authenticationResponse.isSuccess()){
                cookieProvider.setAuthInfo(request,response,loginModel.getEmail(), authenticationResponse);
            }

//            UserSearchBean ub = new UserSearchBean();
//            ub.setEmail(loginModel.getEmail());
//            ub.setPassword(loginModel.getPassword());
//
//            List<UserDto> result = userService.search(ub);
//            List<OrganizationDto> serch = organizationService.search(new OrganizationSearchBean());

//            if (CollectionUtils.isNotEmpty(result)) {
//                resStatus = result.get(0);
//            } else {
//                log.error("no emtity");
//            }

//            String test = null;
//            String userId = null;
//            test = resStatus.getUserType().name();
//            userId = resStatus.getId();
//
//            if (result != null && !result.isEmpty()) {
//                session.setAttribute(CommonWebConstant.currentUser.name(), test);frontendProperties.getContextTestUser())x
//                session.setAttribute(CommonWebConstant.userId.name(), userId);
//
//                loginRes = "success";
//            } else {
//                session.removeAttribute(CommonWebConstant.currentUser.name());
//                loginRes = "wrong";
//            }

            commonResponse.setValue((authenticationResponse.isSuccess())?"success":"wrong");
            commonResponse.setUrl(response.encodeRedirectURL(frontendProperties.getContextRoot()));
            if (commonResponse.getValue().equals("success")){
//                if(authenticationResponse.getAuthToken().getUserRole().equals("TestUser") || authenticationResponse.getAuthToken().getUserRole().equals("TestAdmin")){
//                    commonResponse.setUrl(response.encodeRedirectURL(frontendProperties.getContextRoot()+"test/active-sessions"));
//                }
//                else {
                    commonResponse.setUrl(response.encodeRedirectURL(frontendProperties.getContextRoot()));
//                }
            }

        } else {
            commonResponse.setErrorCode(ErrorCodes.OK);
        }

        return commonResponse;
    }

    @RequestMapping(value = My3oURL.LOGOUT, method = RequestMethod.GET)
    public String processLogout(HttpServletRequest request, HttpServletResponse response) {

        String userId = cookieProvider.getUserId(request);
        authenticationService.logout(userId);
        cookieProvider.invalidate(request, response);

        return "logout-page";
    }

}
