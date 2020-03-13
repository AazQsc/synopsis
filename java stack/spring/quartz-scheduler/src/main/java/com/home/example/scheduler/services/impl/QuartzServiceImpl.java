package com.home.example.scheduler.services.impl;

import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.home.example.scheduler.jobs.AbstractJob;
import com.home.example.scheduler.services.QuartzService;

@Slf4j
@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    @Qualifier(value = "QuartzScheduler")
    private Scheduler scheduler;

    @Override
    public SimpleTrigger createSimpleTrigger(AbstractJob job, JobDataMap jobDataMap, Date startTime) throws SchedulerException {
        String jobName = job.getClass().getName();
        JobDetail jobDetail = createJobDetail(job, jobDataMap, jobName);

        SimpleTrigger trigger = (SimpleTrigger) newTrigger()
            .withIdentity(TriggerKey.createUniqueName(jobName))
            .startAt(startTime)
            .forJob(jobDetail)
            .build();

        scheduler.scheduleJob(jobDetail, trigger);
        return trigger;
    }

    @Override
    public SimpleTrigger createTriggerWithSchedule(AbstractJob job, JobDataMap jobDataMap, Date startTime,
                                                   ScheduleBuilder<SimpleTrigger> scheduleBuilder) throws SchedulerException {
        String jobName = job.getClass().getName();
        JobDetail jobDetail = createJobDetail(job, jobDataMap, jobName);

        SimpleTrigger trigger = newTrigger()
            .withIdentity(TriggerKey.createUniqueName(jobName))
            .startAt(startTime)
            .forJob(jobDetail)
            .withSchedule(scheduleBuilder)
            .build();

        scheduler.scheduleJob(jobDetail, trigger);
        return trigger;
    }

    @Override
    public boolean deleteTrigger(TriggerKey key) throws SchedulerException {
        return scheduler.unscheduleJob(key);
    }

    private JobDetail createJobDetail(AbstractJob job, JobDataMap jobDataMap, String groupName) {
        return JobBuilder.newJob(job.getClass())
            .setJobData(jobDataMap)
            .withIdentity(JobKey.createUniqueName(groupName))
            .build();
    }
}
