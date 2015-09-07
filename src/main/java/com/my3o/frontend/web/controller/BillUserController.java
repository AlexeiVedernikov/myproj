package com.my3o.frontend.web.controller;

import java.util.*;

import com.my3o.backend.service.IBillUserService;
import com.my3o.common.constant.QiwiStatus;
import com.my3o.common.constant.Status;
import com.my3o.common.dto.BillUserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.BillUserSearchBean;
import com.my3o.frontend.constant.My3oURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BillUserController extends AbstractFrontendController {

    @Autowired
    private IBillUserService billUserService;

    @RequestMapping(value = My3oURL.BILL_USER_LIST, method = RequestMethod.GET)
    public String billUserView(HttpServletRequest request, Model model) {

        try {
            String userId = this.getUserId(request);
            List<BillUserDto> bud = new ArrayList<BillUserDto>();
            BillUserSearchBean billUserSearchBean = new BillUserSearchBean();
            List<BillUserDto> budPaid = new ArrayList<BillUserDto>();
            billUserSearchBean.setUserId(userId);
            bud = billUserService.search(billUserSearchBean);

            for(BillUserDto i : bud){
                if(i.getQiwiStatus() != QiwiStatus.paid){
                    budPaid.add(i);
                }
            }

            model.addAttribute("userBillPaid", budPaid);

        } catch (BasicServiceException e) {
            log.error(e.getMessage(), e);
        }

        try {
            String userId = this.getUserId(request);
            List<BillUserDto> bud = new ArrayList<BillUserDto>();
            BillUserSearchBean billUserSearchBean = new BillUserSearchBean();
            billUserSearchBean.setUserId(userId);
            bud = billUserService.search(billUserSearchBean);



            model.addAttribute("userBill", bud);

        } catch (BasicServiceException e) {
            log.error(e.getMessage(), e);
        }

        return "bill-user";
    }
//
//    @RequestMapping(value = My3oURL.BILL_USER_LIST_MORE, method = RequestMethod.GET)
//    public String billUserViewMore(HttpServletRequest request, Model model) {
//
//        try {
//            String userId = this.getUserId(request);
//            List<BillUserDto> bud = new ArrayList<BillUserDto>();
//            BillUserSearchBean billUserSearchBean = new BillUserSearchBean();
//            billUserSearchBean.setUserId(userId);
//            bud = billUserService.search(billUserSearchBean);
//
//
//
//            model.addAttribute("userBill", bud);
//
//        } catch (BasicServiceException e) {
//            log.error(e.getMessage(), e);
//        }
//
//        return "bill-user-more";
//    }
}
