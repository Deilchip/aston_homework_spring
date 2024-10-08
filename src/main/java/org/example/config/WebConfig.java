package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Конфигурация веб-приложения Spring MVC.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.example")
public class WebConfig {
}