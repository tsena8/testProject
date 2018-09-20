package com.tsena.mastermind.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * REST API application Spring configuration
 * @author tsena
 *
 */
@Configuration
@ComponentScan(basePackages= {"com.tsena.mastermind"})
@PropertySource("classpath:app.properties")
public class ApplicationConfig {

}
