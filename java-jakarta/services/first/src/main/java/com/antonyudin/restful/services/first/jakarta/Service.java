
package com.antonyudin.restful.services.first.jakarta;


import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("first")
public class Service implements java.io.Serializable {


	public record OutputJSON(String content) {}

	@XmlRootElement
	@XmlType
	public static class OutputXML implements java.io.Serializable {

		public OutputXML() {
		}

		public OutputXML(final String content) {
			this.content = content;
		}

		private String content;

		@XmlElement
		public String getContent() {
			return content;
		}

		public void setContent(final String value) {
			content = value;
		}
	}

	@Path("processXML")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public OutputXML getOutputXML(final @QueryParam("input") String input) {
		return new OutputXML("output [" + input + "]");
	}

	@Path("processJSON")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public OutputJSON getOutputJSON(final @QueryParam("input") String input) {
		return new OutputJSON("output [" + input + "]");
	}

}

