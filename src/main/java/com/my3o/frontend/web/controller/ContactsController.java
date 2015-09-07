package com.my3o.frontend.web.controller;

import com.my3o.backend.config.IBackendProperties;
import com.my3o.backend.service.*;
import com.my3o.common.constant.ErrorCodes;
import com.my3o.common.constant.UserType;
import com.my3o.common.dto.GroupDto;
import com.my3o.common.dto.UserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.UserSearchBean;
import com.my3o.frontend.constant.My3oURL;
import com.my3o.frontend.web.model.CommonResponse;
import com.my3o.frontend.web.model.ContactWebModel;
import com.my3o.frontend.web.model.ParentChildWebModel;
import com.my3o.frontend.web.model.UserWebModel;
import com.my3o.mail.impl.Sender;
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
 * Created by: andrew
 */
@Controller
public class ContactsController extends AbstractFrontendController {

    @RequestMapping(value = My3oURL.CONTACTS, method = RequestMethod.GET)
    public String contactsView(HttpServletRequest request, Model model, HttpSession session) {

        return "contacts";
    }


    @RequestMapping(value = My3oURL.CONTACTS, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<String> postContact(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            @RequestBody ContactWebModel dataModel) throws Exception {
        CommonResponse commonResponse = new CommonResponse();

        Sender newww = new Sender("info@my3o.ru", "Xtccvfcnth", "smtp.gmail.com", "587");
        newww.send(dataModel.getEmail(), dataModel.getMessage(), "info@my3o.ru");

        commonResponse.setErrorCode(ErrorCodes.OK);

        return commonResponse;
    }

}
