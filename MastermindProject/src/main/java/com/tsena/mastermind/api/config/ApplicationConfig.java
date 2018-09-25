package com.tsena.mastermind.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * REST API application Spring configuration
 * @author tsena
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"com.tsena.mastermind"})
@PropertySource("classpath:app.properties")
public class ApplicationConfig {

}
