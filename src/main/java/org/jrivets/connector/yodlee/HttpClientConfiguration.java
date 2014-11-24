package org.jrivets.connector.yodlee;

/**
 * @author kbabushkin
 * @since 11/23/14
 */

public class HttpClientConfiguration {

    private boolean trustAll;
    private int socketReadTimeout = 10000;
    private int connectionTimeout = 10000;
    private int providerMaxConnections = 10;

    public HttpClientConfiguration(boolean trustAll, int socketReadTimeout, int connectionTimeout, int providerMaxConnections) {
        this.trustAll = trustAll;
        this.socketReadTimeout = socketReadTimeout;
        this.connectionTimeout = connectionTimeout;
        this.providerMaxConnections = providerMaxConnections;
    }

    public HttpClientConfiguration(boolean trustAll) {
        this.trustAll = trustAll;
    }

    public HttpClientConfiguration() {
    }

    public boolean isTrustAll() {
        return trustAll;
    }

    public int getSocketReadTimeout() {
        return socketReadTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getProviderMaxConnections() {
        return providerMaxConnections;
    }
}
