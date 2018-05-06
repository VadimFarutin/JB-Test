package ru.spbau.farutin.stepik_top;

import okhttp3.OkHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * StepikOkHttpClient - http client which trusts stepik.org.
 */
public class StepikOkHttpClient {
    /**
     * Builds http client which trusts stepik.org.
     * @return http client which trusts stepik.org
     * @throws KeyManagementException if failed while initializing SSLContext
     * @throws NoSuchAlgorithmException if failed while initializing SSLContext
     */
    public static OkHttpClient getStepikOkHttpClient()
            throws KeyManagementException, NoSuchAlgorithmException {
        X509TrustManager[] trustAllCerts = new X509TrustManager[] {
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0]);
        builder.hostnameVerifier((hostname, session) -> hostname.equalsIgnoreCase("stepik.org"));

        return builder.build();
    }
}
