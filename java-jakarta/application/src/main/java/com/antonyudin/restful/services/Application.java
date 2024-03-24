
package com.antonyudin.restful.services;


import java.util.Set;

import jakarta.ws.rs.ApplicationPath;


@ApplicationPath("/api")
public class Application extends jakarta.ws.rs.core.Application {


	public Set<Class<?>> getClasses() {
		return Set.of(
			com.antonyudin.restful.services.first.jakarta.Service.class
		);
	}

}

