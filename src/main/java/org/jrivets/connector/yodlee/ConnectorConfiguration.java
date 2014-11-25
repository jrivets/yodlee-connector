package org.jrivets.connector.yodlee;

/**
 * @author kbabushkin
 * @since 11/17/14
 */

public class ConnectorConfiguration {

    private final String hostURL;
    
    private int socketReadTimeout = 10000;
    
    private int connectionTimeout = 10000;
    
    private int maxConnections = 10;

    public ConnectorConfiguration(String hostURL, int socketReadTimeout, int connectionTimeout, int maxConnections) {
        this.hostURL = hostURL;
        this.socketReadTimeout = socketReadTimeout;
        this.connectionTimeout = connectionTimeout;
        this.maxConnections = maxConnections;
    }

    public ConnectorConfiguration(String hostURL) {
        this.hostURL = hostURL;
    }

    public String getHostURL() {
        return hostURL;
    }

    public int getSocketReadTimeout() {
        return socketReadTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    @Override
    public String toString() {
        return "{hostURL=" + hostURL + ", socketReadTimeout=" + socketReadTimeout +
                ", connectionTimeout=" + connectionTimeout + ", maxConnections=" + maxConnections + "}";
    }
}
