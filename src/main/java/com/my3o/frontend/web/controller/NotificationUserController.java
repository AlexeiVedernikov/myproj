package com.my3o.frontend.web.controller;

import com.my3o.backend.service.INotificationService;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.NotificationSearchBean;
import com.my3o.frontend.constant.My3oURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationUserController extends AbstractFrontendController {

    @Autowired
    private INotificationService notificationService;

    @RequestMapping(value = My3oURL.NOTIFICATION_USER_LIST, method = RequestMethod.GET)
    public String billUserView(HttpServletRequest request, Model model) {

        try {
            String userId = this.getUserId(request);
            NotificationSearchBean nsb = new NotificationSearchBean();
            nsb.setUserId(userId);

            model.addAttribute("userNotification", notificationService.search(nsb));

        } catch (BasicServiceException e) {
            log.error(e.getMessage(), e);
        }
        return "notification-user";
    }
}
