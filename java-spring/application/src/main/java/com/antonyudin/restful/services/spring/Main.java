
package com.antonyudin.restful.services.spring;


public class Main {


	public static void main(final String[] argv) throws java.lang.Exception {
		switch (argv[0]) {
			case "server": Server.main(argv); break;
			case "client": Client.main(argv); break;
			default: System.out.println("unknown mode: [" + argv[0] + "]");
		}
	}

}

