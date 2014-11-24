package org.jrivets.connector.yodlee;

/**
 * @author kbabushkin
 * @since 11/17/14
 */

public class ConnectorConfiguration {

    private final String hostURL;

    private final HttpClientConfiguration clientConfiguration;

    public ConnectorConfiguration(String hostURL, HttpClientConfiguration clientConfiguration) {
        this.hostURL = hostURL;
        this.clientConfiguration = clientConfiguration;
    }

    public ConnectorConfiguration(String hostURL) {
        this.hostURL = hostURL;
        this.clientConfiguration = new HttpClientConfiguration();
    }

    public String getHostURL() {
        return hostURL;
    }

    public HttpClientConfiguration getClientConfiguration() {
        return clientConfiguration;
    }
}
