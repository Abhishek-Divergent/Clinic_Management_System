package com.divergent.javaconfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.divergent.javaconfiguration",
		"com.divergent.crud", "com.divergent.databaseconnection", "com.divergent.doa"})
public class javaconfig {

}