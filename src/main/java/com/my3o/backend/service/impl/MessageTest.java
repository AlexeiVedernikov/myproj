//package com.my3o.backend.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.quartz.CronScheduleBuilder;
//import org.quartz.DisallowConcurrentExecution;
//import org.quartz.Job;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.my3o.backend.service.INotificationService;
//import com.my3o.common.dto.NotificationDto;
//import com.my3o.common.dto.UserDto;
//import com.my3o.common.exception.BasicServiceException;
//import com.my3o.common.searchbean.NotificationSearchBean;
//import com.my3o.common.searchbean.UserSearchBean;
//import com.my3o.common.service.task.AbstractBaseDaemonBackgroundTask;
//
//@Service
//@DisallowConcurrentExecution
//public class MessageTest extends AbstractBaseDaemonBackgroundTask implements Job {
//
//    @Autowired
//    private INotificationService notificationService;
//
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        List<NotificationDto> a = new ArrayList<NotificationDto>();
//        NotificationDto nDto = new NotificationDto();
//        UserSearchBean usb = new UserSearchBean();
//        UserDto userDto = new UserDto();
//        try {
//            a = notificationService.search(new NotificationSearchBean());
//        } catch (BasicServiceException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        for (NotificationDto n : a) {
//            JobDetail job = JobBuilder.newJob(MyTestTask.class).withIdentity("Notification" + n.getId(), "group2")
//                    .build();
//            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("Notification" + n.getId(), "group2")
//                    .withSchedule(CronScheduleBuilder.cronSchedule(n.getCronExpression())).build();
//            Scheduler scheduler;
//            try {
//                scheduler = new StdSchedulerFactory().getScheduler();
//                scheduler.start();
//                scheduler.scheduleJob(job, trigger);
//            } catch (SchedulerException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//        }
//
//    }
//
//    @Override
//    public void run() {
//        // TODO Auto-generated method stub
//
//    }
//
// }
