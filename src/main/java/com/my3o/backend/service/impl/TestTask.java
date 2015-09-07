//package com.my3o.backend.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.my3o.backend.service.INotificationService;
//import com.my3o.backend.service.IUserService;
//import com.my3o.common.dto.NotificationDto;
//import com.my3o.common.dto.UserDto;
//import com.my3o.common.exception.BasicServiceException;
//import com.my3o.common.searchbean.NotificationSearchBean;
//import com.my3o.common.searchbean.UserSearchBean;
//import com.my3o.common.service.task.AbstractBaseDaemonBackgroundTask;
//import com.my3o.mail.impl.Sender;
//
//public class TestTask extends AbstractBaseDaemonBackgroundTask {
//
//    @Autowired
//    private INotificationService notificationService;
//
//    @Autowired
//    private IUserService userService;
//
//    @Override
//    public void run() {
//        List<NotificationDto> a = new ArrayList<NotificationDto>();
//        NotificationDto nDto = new NotificationDto();
//        UserSearchBean usb = new UserSearchBean();
//        UserDto userDto = new UserDto();
//
//        try {
//            a = notificationService.search(new NotificationSearchBean());
//        } catch (BasicServiceException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        for (int i = 0; i < a.size(); i++) {
//            try {
//
//                nDto = a.get(i);
//                usb.addKey(nDto.getUserDto().getId());
//                userDto = userService.search(usb).get(0);
//
//                Sender newww = new Sender(nDto.getSettingMailDto().getUserName(), nDto.getSettingMailDto()
//                        .getUserPassword());
//                newww.send(nDto.getSettingMailDto().getDefaultSubjectPrefix(), nDto.getText(), nDto.getSettingMailDto()
//                        .getDefaultSender(), userDto.getEmail());
//
//                log.debug("Notification: " + nDto.getId());
//                // log.debug("All notification was is sent!");
//            } catch (BasicServiceException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
// }
