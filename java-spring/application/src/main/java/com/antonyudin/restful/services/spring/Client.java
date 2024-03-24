
package com.antonyudin.restful.services.spring;


import java.security.cert.X509Certificate;

import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;

import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;

import jakarta.ws.rs.client.ClientBuilder;


public class Client implements java.io.Serializable {


	public static void main(final String[] args) throws java.lang.Exception {

		try {
			final var trustAllCerts = new TrustManager[] { 

				new X509TrustManager() {     

					public X509Certificate[] getAcceptedIssuers() { 
						return null;
					} 

					public void checkClientTrusted( 
						final X509Certificate[] certs, final String authType
					) {
					} 

					public void checkServerTrusted( 
						final X509Certificate[] certs, final String authType
					) {
					}
				} 
			}; 

			final var context = SSLContext.getInstance("TLSv1.3"); 

			context.init(null, trustAllCerts, new java.security.SecureRandom()); 

			final var hostnameVerifier = new HostnameVerifier() {
				public boolean verify(final String hostname, final SSLSession session) {
					System.out.println("verify: [" + hostname + ", " + session + "}");
					return true;
				}
			};

			final var client = ClientBuilder.newBuilder()
				.sslContext(context)
				.hostnameVerifier(hostnameVerifier)
				.build()
			;

			System.out.format("Client: %s\n", client.toString());

			{
				final var response = client
					.target("https://localhost:8080/api/first/processXML?input=test")
					.request("application/xml")
					.get()
				;

				System.out.println("response: [" + response + "]");

				final var entity = response.readEntity(
					com.antonyudin.restful.services.first.spring.Service.OutputXML.class
				);

				System.out.println("entity: [" + entity + "]");
				System.out.println("content: [" + entity.getContent() + "]");
			}

			{
				final var response = client
					.target("https://localhost:8080/api/first/processJSON?input=test")
					.request("application/json")
					.get()
				;

				System.out.println("response: [" + response + "]");

				final var entity = response.readEntity(
					com.antonyudin.restful.services.first.spring.Service.OutputJSON.class
				);

				System.out.println("entity: [" + entity + "]");
				System.out.println("content: [" + entity.content() + "]");
			}

		} catch (java.lang.Exception exception) {
			exception.printStackTrace(System.out);
		}

	}

}

