
if ! test -f key.store; then
	keytool				\
	-genkey				\
	-alias server			\
	-keyalg RSA			\
	-storepass test123		\
	-keystore key.store		\
	-dname "CN=localhost, OU=Unknown, O=Unknown, L=Unknown, S=ME, C=US"
fi


#
#	"-Djavax.net.debug=ssl:handshake"

java								\
	-classpath target/application-1.0.jar			\
	"-Djava.util.logging.config.file=logging.properties"	\
	"-Djavax.net.ssl.keyStore=key.store"			\
	"-Djavax.net.ssl.keyStorePassword=test123"		\
	com.antonyudin.restful.services.Server

