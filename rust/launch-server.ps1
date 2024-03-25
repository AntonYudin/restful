
$openssl='C:\Program Files\OpenSSL-Win64\bin\openssl.exe'

if (-Not(Test-Path "key.store.p12")) {

	keytool										`
		-genkey									`
		-alias server								`
		-keyalg RSA								`
		-storepass test123							`
		-keystore key.store.p12							`
		-dname "CN=localhost, OU=Unknown, O=Unknown, L=Unknown, S=ME, C=US"

	& $openssl pkcs12 -info -in key.store.p12 -passin 'pass:test123' -nodes 

	& $openssl pkcs12 -in key.store.p12 -passin 'pass:test123' -nokeys -nodes -out server.cert.pem
	& $openssl pkcs12 -in key.store.p12 -passin 'pass:test123' -nocerts -nodes -out server.key.pem

}


./target/debug/server.exe

