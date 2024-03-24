
if (-Not(Test-Path "key.store")) {
	keytool										`
		-genkey									`
		-alias server								`
		-keyalg RSA								`
		-storepass test123							`
		-keystore key.store							`
		-dname "CN=localhost, OU=Unknown, O=Unknown, L=Unknown, S=ME, C=US"
}

java								`
	"-Djavax.net.ssl.keyStore=key.store"			`
	"-Djavax.net.ssl.keyStorePassword=test123"		`
	-jar target/application-1.0.jar server

