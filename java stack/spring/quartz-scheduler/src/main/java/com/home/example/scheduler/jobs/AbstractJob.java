package com.home.example.scheduler.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * todo
 * @param <S> service to be used by the job.
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public abstract class AbstractJob<S> implements Job {

    @Autowired
    protected S service;
}
