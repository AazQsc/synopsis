package com.home.example.scheduler.config;

import java.util.Properties;
import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.home.example.scheduler.config.properties.JobsProperties;
import com.home.example.scheduler.config.properties.SchedulerProperties;

/**
 * Main quartz configuration class
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class QuartzConfig {

    private final ApplicationContext applicationContext;

    private final DataSource dataSource;

    private final AutowiringQuartzJobFactory autowiringQuartzJobFactory;

    private final SchedulerProperties schedulerProperties;

    private final JobsProperties jobsProperties;

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        autowiringQuartzJobFactory.setApplicationContext(applicationContext);
        return autowiringQuartzJobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactory(@Autowired(required = false) Trigger... triggers) throws Exception {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();

        Properties properties = new Properties();
        properties.setProperty("org.quartz.jobStore.driverDelegateClass", schedulerProperties.getDriverClass());
        properties.setProperty("org.quartz.jobStore.tablePrefix", schedulerProperties.getTablePrefix());

        schedulerFactory.setQuartzProperties(properties);
        schedulerFactory.setDataSource(dataSource);
        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setOverwriteExistingJobs(jobsProperties.getOverwriteExistingJobs());
        schedulerFactory.setWaitForJobsToCompleteOnShutdown(jobsProperties.getWaitJobsOnShutdown());
        schedulerFactory.afterPropertiesSet();

        if (triggers != null) {
            schedulerFactory.setTriggers(triggers);
        }
        return schedulerFactory;
    }

    @Bean(name = "QuartzScheduler")
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) {
        return schedulerFactoryBean.getScheduler();
    }
}
