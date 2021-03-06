package com.my3o.frontend.web.security;

import com.my3o.backend.service.IAuthenticationService;
import com.my3o.common.dto.AuthToken;
import com.my3o.common.dto.AuthenticationResponse;
import com.my3o.frontend.config.IFrontendProperties;
import com.my3o.frontend.web.model.My3oAuthCookie;
import com.my3o.frontend.web.util.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.Date;

@Component
public class My3oCookieProvider {

    private static Logger LOG = Logger.getLogger(My3oCookieProvider.class);

    @Autowired
    private CookieCryptor cookieCryptor;

    @Autowired
    private IFrontendProperties frontendProperties;
    @Autowired
    private IAuthenticationService authenticationService;

    private static final String COOKIE_NAME = "MY3O_AUTH_TOKEN";
    private static final String COOKIE_RENEWED_ATTR = "AUTH_COOKIE_RENEWED";
    private static final String COOKIE_RENEW_FAILED = "AUTH_COOKIE_RENEW_FAILED";



//    @Value("${org.openiam.auth.cookie.domain}")
//    private String cookieDomain;

//    @Value("${org.openiam.ui.auth.cookie.encryption.enabled}")
    private boolean cookieEncryptionEnabled=true;
    
    //@Value("${org.openiam.ui.auth.cookie.include.timestamp}")
    //private boolean includeTimestamp;

    /**
     * Called to renew the current Authentication Token
     * 
     * @param request
     *            - the current HttpServletRequest
     * @return - the new OpenIAMAuthCookie, representing the identity of the
     *         User
     */
    public My3oAuthCookie renew(final HttpServletRequest request, final HttpServletResponse response) {
        My3oAuthCookie retVal = null;
        final My3oAuthCookie oldCookie = decrypt(request);
        String user_Id = this.getUserId(request);
//        if (!ProxyFilter.isProxyRequest(request)) {
//            LOG.debug("is not a proxy request - renewing cookie");
            if (oldCookie != null) {

                final AuthenticationResponse authResponse = authenticationService.renewToken(oldCookie.getPrincipal(), oldCookie.getToken(), user_Id);
                if(oldCookie.isExpired()) {
                    authResponse.fail();
                }

                if (authResponse != null && authResponse.isSuccess()) {
                    final AuthToken authToken = (AuthToken) authResponse.getAuthToken();
                    if (authToken != null) {
                        final String token = authToken.getToken();
                        final String userRole = authToken.getUserRole();
                        final String userId = authToken.getUserId();
                        final String principal = authToken.getPrincipal();
                        try {
                            //retVal = new OpenIAMAuthCookie(userId, principal, token, tokenType, (includeTimestamp) ? ssoToken.getExpirationTime() : null);
                            retVal = new My3oAuthCookie(userId, principal, token, userRole, authToken.getExpirationTime());
                            encryptAndAdd(request, response, retVal, authToken);
                            request.setAttribute(COOKIE_RENEWED_ATTR, retVal);
                        } catch (Throwable e) {
                            LOG.warn(String.format("Can't encrypt cookie", authToken), e);
                        }
                    }
                } else {
                    request.setAttribute(COOKIE_RENEW_FAILED, true);
                }
            }
//        } else {
//            LOG.debug("Is proxy request - using cookie from proxy");
//            retVal = oldCookie;
//            request.setAttribute(COOKIE_RENEWED_ATTR, retVal);
//        }
        return retVal;
    }

    public boolean cookieRenewFailedForThisRequest(final HttpServletRequest request) {
        return Boolean.TRUE.equals(request.getAttribute(COOKIE_RENEW_FAILED));
    }

    public My3oAuthCookie getTokenForCurrentRequest(final HttpServletRequest request) {
        return (My3oAuthCookie) request.getAttribute(COOKIE_RENEWED_ATTR);
    }

    /**
     * Removes the AuthCookie from your request
     * 
     * @param response
     *            - HttpServletResponse of this request
     */
    public void invalidate(final HttpServletRequest request, final HttpServletResponse response) {
        final Cookie cookie = CookieUtils.getCookie(request, COOKIE_NAME);
        if (cookie != null) {
            cookie.setDomain(getCookieDomain());
            cookie.setValue(null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        final Cookie cookieOrganizationId = CookieUtils.getCookie(request, "organizationId");
        if (cookieOrganizationId != null) {
            cookieOrganizationId.setDomain(getCookieDomain());
            cookieOrganizationId.setValue(null);
            cookieOrganizationId.setMaxAge(0);
            cookieOrganizationId.setPath("/");
            response.addCookie(cookieOrganizationId);
        }
    }

    public String getUserId(final HttpServletRequest request) {
        String userId = null;
        final My3oAuthCookie token = decrypt(request);
        if (token != null) {
            userId = token.getUserId();
        }
        return userId;
    }
    public String getUserRole(final HttpServletRequest request) {
        String userRole = null;
        final My3oAuthCookie token = decrypt(request);
        if (token != null) {
            userRole = token.getUserRole();
        }
        return userRole;
    }

    public String getOrganizationId(final HttpServletRequest request) {
        String organizationId = null;
        Cookie cookie = CookieUtils.getCookie(request, "organizationId");
        if (cookie != null) {
            organizationId = cookie.getValue();
        }
        return organizationId;
    }

    public String getPrincipal(final HttpServletRequest request) {
        String principal = null;
        final My3oAuthCookie cookie = this.decrypt(request);
        if (cookie != null) {
            principal = cookie.getPrincipal();
        }
        return principal;
    }

    /**
     * 
     * @param request
     *            - the current HttpServletRequest
     * @return - the current OpenIAMAuthCookie - does NOT renew the token
     */
    private My3oAuthCookie decrypt(final HttpServletRequest request) {
        My3oAuthCookie retVal = null;
        final Cookie cookie = CookieUtils.getCookie(request, COOKIE_NAME);

        if (cookie != null && cookie.getMaxAge() != 0) {
            try {
                final String cookieValue = (cookieEncryptionEnabled) ? cookieCryptor.decode(cookie.getValue()) : cookie.getValue();
                retVal = My3oAuthCookie.getToken(cookieValue);
            } catch (Throwable e) {
                LOG.warn(String.format("Can't decrypt cookie", cookie.getValue()), e);
            }
        }

        return retVal;
    }

    public void setAuthInfo(final HttpServletRequest request, final HttpServletResponse response, final String principal,
                            final AuthenticationResponse authResponse) throws Exception {
        final String userId = authResponse.getUserId();

        final AuthToken authToken = authResponse.getAuthToken();
        final My3oAuthCookie token = new My3oAuthCookie(userId, principal, authToken.getToken(), authToken.getUserRole(), authToken.getExpirationTime());
        encryptAndAdd(request, response, token, authToken);
    }

    private void encryptAndAdd(final HttpServletRequest request, final HttpServletResponse response, final My3oAuthCookie token,
                               final AuthToken authToken) throws Exception {
        final int seconds = Long.valueOf((authToken.getExpirationTime().getTime() - new Date().getTime()) / 1000).intValue();

        final String cookieValue = (cookieEncryptionEnabled) ? cookieCryptor.encode(token.tokenizeValue()) : token.tokenizeValue();
        final Cookie cookie = new Cookie(COOKIE_NAME, cookieValue);
        if(frontendProperties.expireOnBroserClose()) {
        	cookie.setMaxAge(-1);
        } else {
        	cookie.setMaxAge(seconds);
        }
        cookie.setPath("/");
        cookie.setSecure(StringUtils.equalsIgnoreCase("https", request.getScheme()));
        cookie.setDomain(getCookieDomain());
        response.addCookie(cookie);
    }

    private String getCookieDomain() {
        return frontendProperties.getCookieDomain();
    }

    public void setOrganizationId(final HttpServletRequest request, final HttpServletResponse response, final String cookieValue) throws Exception {


        final Cookie cookie = new Cookie("organizationId", cookieValue);
        if(frontendProperties.expireOnBroserClose()) {
            cookie.setMaxAge(-1);
        } else {
            cookie.setMaxAge(999999); //TODO make second like encryptAndAdd(...)
        }
        cookie.setPath("/");
        cookie.setSecure(StringUtils.equalsIgnoreCase("https", request.getScheme()));
        cookie.setDomain(getCookieDomain());
        response.addCookie(cookie);

    }
}
