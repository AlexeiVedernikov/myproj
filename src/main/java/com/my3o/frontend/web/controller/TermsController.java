package com.my3o.frontend.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my3o.frontend.constant.My3oURL;

@Controller
public class TermsController extends AbstractFrontendController {

    @RequestMapping(value = My3oURL.TERMS_LIST, method = RequestMethod.GET)
    public String termsView(HttpServletRequest request, Model model) {

        return "terms";
    }

    @RequestMapping(value = My3oURL.INSTRUCTIONS_LIST, method = RequestMethod.GET)
    public String instructionView(HttpServletRequest request, Model model) {

        return "instructions";
    }
}
