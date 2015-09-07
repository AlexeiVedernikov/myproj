package com.my3o.frontend.web.controller;

import com.my3o.frontend.config.IFrontendProperties;
import com.my3o.frontend.constant.CommonWebConstant;
import com.my3o.frontend.web.security.My3oCookieProvider;
import com.my3o.frontend.web.util.CookieUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author Alexander Duckardt
 * 
 */
public abstract class AbstractFrontendController {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected IFrontendProperties frontendProperties;
    @Autowired
    protected My3oCookieProvider cookieProvider;
    @Autowired
    private ObjectMapper mapper;

    public void writeResponse(HttpServletResponse response, Object responseModel, String callbackFunction)
            throws JsonGenerationException, JsonMappingException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(callbackFunction).append("(").append(mapper.writeValueAsString(responseModel)).append(")");
        response.getOutputStream().write(sb.toString().getBytes("UTF-8"));
    }

    @ModelAttribute("currentUser")
    public String getCurrentUser(HttpSession session) {
        if (session.getAttribute(CommonWebConstant.currentUser.name()) != null) {
            return (String) session.getAttribute(CommonWebConstant.currentUser.name());
        }

        return null;
    }

    @ModelAttribute("trueAnswer")
    public String getTrueAnswer(HttpSession session) {
        if (session.getAttribute(CommonWebConstant.trueAnswer.name()) != null) {
            return (String) session.getAttribute(CommonWebConstant.trueAnswer.name());
        }

        return null;
    }

    @ModelAttribute("allTask")
    public String getAllTask(HttpSession session) {
        if (session.getAttribute(CommonWebConstant.allTask.name()) != null) {
            return (String) session.getAttribute(CommonWebConstant.allTask.name());
        }

        return null;
    }

    @ModelAttribute("seanceId")
    public String getSeanceId(HttpSession session) {
        if (session.getAttribute(CommonWebConstant.seanceId.name()) != null) {
            return (String) session.getAttribute(CommonWebConstant.seanceId.name());
        }

        return null;
    }


    @ModelAttribute("userRole")
    public String getUserRole(HttpServletRequest request) {
        return cookieProvider.getUserRole(request);
    }

    @ModelAttribute("userId")
    public String getUserId(HttpServletRequest request) {
        return cookieProvider.getUserId(request);
    }

//    @ModelAttribute("listOrg")
//    public Map<String, String> getListOrg(HttpSession session) {
//        if (session.getAttribute(CommonWebConstant.listOrg.name()) != null) {
//            return (Map<String, String>) session.getAttribute(CommonWebConstant.listOrg.name());
//        }
//
//        return null;
//    }

//    @ModelAttribute("selectOrg")
//    public String getSelectOrgId(HttpSession session) {
//        if (session.getAttribute(CommonWebConstant.selectOrg.name()) != null) {
//            return (String) session.getAttribute(CommonWebConstant.selectOrg.name());
//        }
//
//        return null;
//    }
}
