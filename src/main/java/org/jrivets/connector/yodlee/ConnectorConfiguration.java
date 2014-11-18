package org.jrivets.connector.yodlee;

/**
 * @author kbabushkin
 * @since 11/17/14
 */

public class ConnectorConfiguration {

    private final String hostURL;
    private final String apiVersion;

    public ConnectorConfiguration(ConnectorConfiguration configuration) {
        this.hostURL = configuration.hostURL;
        this.apiVersion = configuration.apiVersion;
    }

    public ConnectorConfiguration(String hostURL, String apiVersion) {
        this.hostURL = hostURL;
        this.apiVersion = apiVersion;
    }

    public String getHostURL() {
        return hostURL;
    }

    public String getApiVersion() {
        return apiVersion;
    }
}
