package com.divergent.javaconfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = { "com.divergent.javaconfiguration", "com.divergent.crud",
		"com.divergent.databaseconnection", "com.divergent.doa","com.divergent.dto" })
@Profile("dev")
@PropertySource({"classpath:/clinicmanagement.properties"})
public class JavaConfig {

}