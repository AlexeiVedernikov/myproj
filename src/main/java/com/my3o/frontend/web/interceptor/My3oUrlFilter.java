package com.my3o.frontend.web.interceptor;

import com.my3o.frontend.web.security.http.My3oHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by: Alexander Duckardt
 * Date: 8/3/14.
 */
public class My3oUrlFilter implements Filter {
    protected static final Logger log = LoggerFactory.getLogger(My3oUrlFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("================ My3oUrlFilter handle the request =======================");
        final HttpServletRequest httpRequest = (HttpServletRequest)request;
        final HttpServletResponse httpResponse = (HttpServletResponse)response;


        chain.doFilter(new My3oHttpServletRequest(httpRequest, true), response);
    }

    @Override
    public void destroy() {

    }
}
