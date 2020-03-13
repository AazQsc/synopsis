package com.home.example.scheduler.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**
 * This factory adds beans created at runtime to the context.
 * <p>
 * Each job is a bean that is associated with some kind of service.
 * To @Autowired the service to be successful, the job needs to be added to the context.
 * todo research it
 */
@Component
public class AutowiringQuartzJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    @Autowired
    private transient AutowireCapableBeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        beanFactory = context.getAutowireCapableBeanFactory();
    }

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object job = super.createJobInstance(bundle);

        beanFactory.autowireBean(job);
        return job;
    }
}
