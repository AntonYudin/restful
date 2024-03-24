
package com.antonyudin.restful.services.spring;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

import com.antonyudin.restful.services.first.spring.Service;


@SpringBootApplication
@ComponentScan(basePackageClasses = Service.class)
public class Server implements java.io.Serializable {

	public static void main(final String[] args) throws java.lang.Exception {
		SpringApplication.run(Server.class, args);
	}

}

