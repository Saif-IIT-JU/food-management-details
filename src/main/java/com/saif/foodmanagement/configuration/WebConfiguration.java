package com.saif.foodmanagement.configuration;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author saifuzzaman
 */
@Configuration
@ServletComponentScan(basePackages = {"com.saif.foodmanagement.filters"})
public class WebConfiguration {
}
