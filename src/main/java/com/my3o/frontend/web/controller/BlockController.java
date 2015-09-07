package com.my3o.frontend.web.controller;

import com.my3o.frontend.constant.My3oURL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BlockController extends AbstractFrontendController {

    @RequestMapping(value = My3oURL.BLOCK_LIST, method = RequestMethod.GET)
    public String blocksView(HttpServletRequest request, Model model) {

        return "block";
    }
}