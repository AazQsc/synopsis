package com.home.example.scheduler.config.properties;

import javax.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@ConfigurationProperties("quartz.jobs")
public class JobsProperties {

    @NotNull
    private Boolean overwriteExistingJobs;

    @NotNull
    private Boolean waitJobsOnShutdown;

}
