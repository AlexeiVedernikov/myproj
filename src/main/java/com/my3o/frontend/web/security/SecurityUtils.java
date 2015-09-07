package com.my3o.frontend.web.security;

import com.my3o.common.constant.UserType;
import com.my3o.frontend.config.IFrontendProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by: Alexander Duckardt
 * Date: 8/15/14.
 */
public class SecurityUtils {
    protected static final Logger log = LoggerFactory.getLogger(SecurityUtils.class);

    private static IFrontendProperties frontendProperties;
    private static boolean isInitialized=false;


    public static boolean isURLOpen(HttpServletRequest request) {
        return isURLOpen(request, request.getRequestURI());
    }

    public static boolean isURLAllowed(UserType userRole, HttpServletRequest request) {
//        log.debug("requestURI: {}",request.getRequestURI());

//        final String requestedURL = (request.getRequestURI().endsWith("/") )
//                                        ? request.getRequestURI().substring(0,request.getRequestURI().length()-1)
//                                        :request.getRequestURI();

//        String requestedURL = request.getRequestURI();
//        log.debug("formatted requestURI: {}",requestedURL);
//
//        if(request.getContextPath().equals(requestedURL))
//            return true;
//        if(UserType.SuperAdmin == userRole)
//            return true;
//
//
//        for(Privilege privilege: My3oPermissions.userPermissions[userRole.ordinal()].getPrivileges()){
//            String allowedUrl = request.getContextPath() + privilege.getURL();
//            if(allowedUrl.equals(request.getRequestURI())){
//                if("GET".equals(request.getMethod())
//                   || privilege.isCanWrite())
//                    return true;
//            }
//        }
        return isURLAllowed(userRole, request, request.getRequestURI());
    }


    public static boolean isURLOpen(HttpServletRequest request, String requestURI) {
        return isURLAllowed(UserType.Other, request, requestURI);
    }

    public static boolean isURLAllowed(UserType userRole, HttpServletRequest request, String requestURI) {
        if(!isInitialized){
            init(request);
        }
        log.debug("requestURI: {}",requestURI);

//        final String requestedURL = (requestURI.endsWith("/") )
//                                        ? requestURI.substring(0, requestURI.length() - 1)
//                                        :requestURI;

        String formattedURL = requestURI.replaceFirst(frontendProperties.getContextRoot(),"");
        log.debug("formatted requestURI: {}",formattedURL);

        if("".equals(formattedURL)
           || "/".equals(formattedURL))
            return true;
        if(UserType.SuperAdmin == userRole)
            return true;


        for(Privilege privilege: My3oPermissions.userPermissions[userRole.ordinal()].getPrivileges()){
//            String allowedUrl = frontendProperties.getContextRoot() + privilege.getURL();
            if(privilege.getURL().equals(formattedURL)){
                if("GET".equals(request.getMethod())
                   || privilege.isCanWrite())
                    return true;
            }
        }
        return false;
    }


    private static void init(HttpServletRequest request){
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(
                request.getServletContext());
        frontendProperties = ctx.getBean(IFrontendProperties.class);
        isInitialized=true;
    }
}
