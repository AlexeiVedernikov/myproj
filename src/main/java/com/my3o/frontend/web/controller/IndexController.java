package com.my3o.frontend.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.my3o.backend.config.IBackendProperties;
import com.my3o.frontend.config.IFrontendProperties;
import com.my3o.frontend.constant.My3oURL;
import com.my3o.sms24x7.SMS24x7;
import com.my3o.sms24x7.SMS24x7Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my3o.backend.service.IOrganizationService;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.OrganizationSearchBean;

import java.io.IOException;

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
@Controller
public class IndexController extends AbstractFrontendController {

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private IBackendProperties backendProperties;





    @RequestMapping(value = { My3oURL.START, My3oURL.INDEX }, method = RequestMethod.GET)
    public String getIndexView(HttpServletRequest request, Model model, HttpSession session) {


//        try {
//            SMS24x7Impl sms24x7 = new SMS24x7Impl();
//            sms24x7.login("info.my3o@gmail.com", "XcGY3F4");
//            sms24x7.send("+7XXXXXXXXXX", "79515273286", "hello5");
//            sms24x7.logout();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return "index";
    }
}
