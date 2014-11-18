package org.jrivets.connector.yodlee;

/**
 * @author kbabushkin
 * @since 11/17/14
 */

public class YodleeConnector {

    private ConnectorConfiguration configuration;

    public YodleeConnector(ConnectorConfiguration configuration) {
        this.configuration = new ConnectorConfiguration(configuration);
    }

    public CobrandSessionInfo loginCobrand(CobrandCredentials credentials) {
        return null;
    }

    public UserSessionInfo loginUser(UserCredentials credentials) {
        return null;
    }
}
