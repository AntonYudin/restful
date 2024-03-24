
package com.antonyudin.restful.services;


import jakarta.ws.rs.SeBootstrap;
import jakarta.ws.rs.SeBootstrap.Configuration.SSLClientAuthentication;


public class Server implements java.io.Serializable {


	public static void main(final String[] args) throws java.lang.Exception {

		final var application = new Application();

		SeBootstrap.start(
			application, 
			SeBootstrap.Configuration.builder()
				.protocol("https")
				.host("0.0.0.0")
				.port(8081)
				.sslClientAuthentication(SSLClientAuthentication.NONE)
				.build()
		).thenAccept(
			instance -> {
				instance.stopOnShutdown(
					stopResult -> {
						System.out.println("stopped" + stopResult.unwrap(Object.class));
					}
				);
				System.out.println("server: " + instance.configuration().baseUri());
			}
		).toCompletableFuture()
		.join();

		Thread.currentThread().join();
	}

}

