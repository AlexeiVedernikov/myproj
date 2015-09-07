//package com.my3o.backend.config;
//
//import org.quartz.CronScheduleBuilder;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.JobKey;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.TriggerKey;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.my3o.backend.service.INotificationService;
//import com.my3o.backend.service.impl.MessageTest;
//
//@Component
//public class CronScheluder {
//
////    @Autowired
////    private INotificationService notificationService;
////
////    @Autowired
////    private MessageTest messageTest;
////
////    public CronScheluder() throws Exception {
////
////        JobKey jobKey = new JobKey("key");
////        JobDetail job = JobBuilder.newJob(MessageTest.class).withIdentity(jobKey.toString(), "group1").build();
////
////        TriggerKey triggerKey = new TriggerKey("triggerKey");
////        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey.toString(), "group1")
////                .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?")).build();
////        Scheduler scheduler;
////        try {
////            scheduler = new StdSchedulerFactory().getScheduler();
////            scheduler.start();
////            scheduler.scheduleJob(job, trigger);
////        } catch (SchedulerException e) {
////            // TODO Auto-generated catch block
////            e.printStackTrace();
////        }
////    }
//
// }
