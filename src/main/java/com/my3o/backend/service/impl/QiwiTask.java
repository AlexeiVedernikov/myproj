package com.my3o.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.my3o.backend.service.IBillUserService;
import com.my3o.backend.service.IUserService;
import com.my3o.common.constant.QiwiStatus;
import com.my3o.common.dto.BillUserDto;
import com.my3o.common.exception.BasicServiceException;
import com.my3o.common.searchbean.BillUserSearchBean;
import com.my3o.common.service.task.AbstractBaseDaemonBackgroundTask;
import com.my3o.rest.client.QiwiAPIClient;
import com.my3o.rest.client.impl.QiwiClient;
import com.my3o.rest.response.QiwiResponseWrapper;

public class QiwiTask extends AbstractBaseDaemonBackgroundTask {

    @Autowired
    private IBillUserService billUserService;

    @Autowired
    private IUserService userService;

    @Autowired
    QiwiClient qc;

    @Autowired
    QiwiAPIClient apq;

    @Override
    public void run() {
        List<BillUserDto> all = new ArrayList<BillUserDto>();
        List<BillUserDto> needStat = new ArrayList<BillUserDto>();
        try {
            all = billUserService.search(new BillUserSearchBean());
            all.get(0).getQiwiStatus();
            for (BillUserDto i : all) {
                if (i.getQiwiStatus() == QiwiStatus.New || i.getQiwiStatus() == QiwiStatus.waiting) {
                    needStat.add(i);
                }
            }

            for (BillUserDto i : needStat) {
                log.debug(i.getQiwiStatus().toString());
                try {
                    QiwiResponseWrapper resp1 = qc.billRequest(i.getPrvId(), i.getId());
                    log.debug(resp1.getResponse().getBill().getStatus());
                    i.setQiwiStatus(QiwiStatus.valueOf(resp1.getResponse().getBill().getStatus()));
                    billUserService.save(i);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        } catch (BasicServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        log.debug("Qiwi this");

    }
}
