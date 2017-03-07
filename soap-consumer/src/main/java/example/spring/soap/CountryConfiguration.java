
package example.spring.soap;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.HttpsUrlConnectionMessageSender;

@Configuration
public class CountryConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CountryConfiguration.class);

    @Value("${apitest.ws.key-store}")
    private Resource keyStore;

    @Value("${apitest.ws.key-store-password}")
    private String keyStorePassword;

    @Value("${apitest.ws.trust-store}")
    private Resource trustStore;

    @Value("${apitest.ws.trust-store-password}")
    private String trustStorePassword;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage>
        // specified in pom.xml
        marshaller.setContextPath(Constants.PACKAGE_PATH);
        return marshaller;
    }

    @Bean
    public CountryClient quoteClient(Jaxb2Marshaller marshaller) throws Exception {
        CountryClient client = new CountryClient();
        client.setDefaultUri(Constants.URL_WS);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);

        KeyStore ks = KeyStore.getInstance("JKS");
        InputStream keyStoreInputStream = keyStore.getInputStream();
        ks.load(keyStoreInputStream, keyStorePassword.toCharArray());
        keyStoreInputStream.close();
        log.info("Loaded keystore: " + keyStore.getURI().toString());

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(ks, keyStorePassword.toCharArray());

        KeyStore ts = KeyStore.getInstance("JKS");
        InputStream trustStoreInputStream = trustStore.getInputStream();
        ts.load(trustStoreInputStream, trustStorePassword.toCharArray());
        trustStoreInputStream.close();
        log.info("Loaded trustStore: " + trustStore.getURI().toString());

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(ts);

        HttpsUrlConnectionMessageSender messageSender = new HttpsUrlConnectionMessageSender();
        messageSender.setKeyManagers(keyManagerFactory.getKeyManagers());
        messageSender.setTrustManagers(trustManagerFactory.getTrustManagers());

        // otherwise: java.security.cert.CertificateException: No name matching
        // localhost found
        messageSender.setHostnameVerifier((hostname, sslSession) -> {
            if (hostname.equals("localhost")) {
                return true;
            }
            return false;
        });

        client.setMessageSender(messageSender);

        return client;

    }

}
