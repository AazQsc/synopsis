package com.home.example.scheduler.services;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.ScheduleBuilder;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerKey;

import com.home.example.scheduler.jobs.AbstractJob;

public interface QuartzService {

    SimpleTrigger createSimpleTrigger(AbstractJob job, JobDataMap jobDataMap, Date startTime) throws SchedulerException;

    SimpleTrigger createTriggerWithSchedule(AbstractJob job, JobDataMap jobDataMap, Date startTime,
                                            ScheduleBuilder<SimpleTrigger> scheduleBuilder) throws SchedulerException;

    boolean deleteTrigger(TriggerKey key) throws SchedulerException;
}
