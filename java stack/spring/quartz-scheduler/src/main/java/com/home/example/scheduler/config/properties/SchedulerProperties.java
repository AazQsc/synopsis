package com.home.example.scheduler.config.properties;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@ConfigurationProperties("quartz.scheduler")
public class SchedulerProperties {

    @NotBlank
    private String driverClass;

    @NotBlank
    private String tablePrefix;

}
