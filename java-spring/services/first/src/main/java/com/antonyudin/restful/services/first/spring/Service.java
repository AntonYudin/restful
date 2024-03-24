
package com.antonyudin.restful.services.first.spring;


import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
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

	@GetMapping(value = "/api/first/processJSON", produces = "application/json")
	public OutputJSON processJSON(final @RequestParam(name = "input", required = false) String input) {
		return new OutputJSON("output [" + input + "]");
	}

	@GetMapping(value = "/api/first/processXML", produces = "application/xml")
	public OutputXML getOutputXML(final @RequestParam(name = "input", required = false) String input) {
		return new OutputXML("output [" + input + "]");
	}

}

