
$openssl='C:\Program Files\Git\usr\bin\openssl.exe'

rm key.store.p12

keytool				`
	-genkey			`
	-keyalg rsa		`
	-alias server		`
	-keystore key.store.p12	`
	-storepass 'test123'	`
	-storetype PKCS12	`
	-dname 'CN=localhost'

& $openssl pkcs12 -info -in key.store.p12 -passin 'pass:test123' -nodes 

& $openssl pkcs12 -in key.store.p12 -passin 'pass:test123' -nokeys -nodes -out server.crt
& $openssl pkcs12 -in key.store.p12 -passin 'pass:test123' -nocerts -nodes -out server.key

./server.exe
