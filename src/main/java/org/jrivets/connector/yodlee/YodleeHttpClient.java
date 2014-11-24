package org.jrivets.connector.yodlee;

import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.jrivets.connector.util.JsonSerializer;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * @author kbabushkin
 * @since 11/20/14
 */

final class YodleeHttpClient extends DefaultHttpClient {

    private final String baseURL;

    private final HttpClient httpClient;

    YodleeHttpClient(String baseURL) {
        this.baseURL = baseURL;
        this.httpClient = buildHttpClient(new HttpClientConfiguration());
    }

    YodleeHttpClient(String baseURL, HttpClientConfiguration configuration) {
        this.baseURL = baseURL;
        this.httpClient = buildHttpClient(configuration);
    }

    HttpClient getHttpClient() {
        return httpClient;
    }

    public <T> T post(String relativePath,  NameValuePair[] bodyParams, Class<T> clazz) throws IOException, URISyntaxException {
        Request request = Request.Post(buildRequestURL(relativePath))
                .bodyForm(bodyParams);

        Response response = Executor.newInstance(getHttpClient()).execute(request);

        return JsonSerializer.fromJson(EntityUtils
                .toString(response.returnResponse().getEntity(), "UTF-8"), clazz);
    }

    private String buildRequestURL(String path) throws URISyntaxException {
        return new URI(baseURL + path).toString();
    }

    private HttpClient buildHttpClient(HttpClientConfiguration configuration) {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 8080, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(new Scheme("https", 443, configuration.isTrustAll() ?
                buildTrustAllSSLSocketFactory() : SSLSocketFactory.getSocketFactory()));

        PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager(schemeRegistry);
        connectionManager.setDefaultMaxPerRoute(configuration.getProviderMaxConnections());
        connectionManager.setMaxTotal(configuration.getProviderMaxConnections());

        HttpParams httpParams = new BasicHttpParams();
        httpParams.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, configuration.getSocketReadTimeout());
        httpParams.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, configuration.getConnectionTimeout());

        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParams, "UTF-8");

        DefaultHttpClient httpClient = new DefaultHttpClient(connectionManager);
        httpClient.setParams(httpParams);
        return httpClient;
    }

    private SSLSocketFactory buildTrustAllSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }}, new SecureRandom());

            return new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            return null;
        }
    }
}
