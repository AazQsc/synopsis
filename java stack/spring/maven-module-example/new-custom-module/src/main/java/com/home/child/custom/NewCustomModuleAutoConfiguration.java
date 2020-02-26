package com.epam.prj.databinding;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// to find all beans
@Configuration
@ComponentScan(value = {"com.home.child.custom"})
public class DataBindingAutoConfiguration {

}
