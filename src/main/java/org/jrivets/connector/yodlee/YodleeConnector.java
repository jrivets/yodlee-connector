package org.jrivets.connector.yodlee;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jrivets.connector.yodlee.login.CobrandCredentials;
import org.jrivets.connector.yodlee.login.CobrandSession;
import org.jrivets.connector.yodlee.login.UserCredentials;
import org.jrivets.connector.yodlee.login.UserSession;
import org.jrivets.connector.yodlee.transaction.TransactionSearchRequest;
import org.jrivets.connector.yodlee.transaction.TransactionSearchResult;

/**
 * @author kbabushkin
 * @since 11/17/14
 */

public final class YodleeConnector {

    private final YodleeHttpClient httpClient;

    public YodleeConnector(ConnectorConfiguration configuration) {
        this.httpClient = new YodleeHttpClient(configuration.getHostURL(), configuration.getClientConfiguration());
    }

    public CobrandSession cobrandLogin(CobrandCredentials cobCredentials) throws YodleeConnectorException {
        NameValuePair[] bodyParams = new NameValuePair[] {
                new BasicNameValuePair("cobrandLogin", cobCredentials.getLogin()),
                new BasicNameValuePair("cobrandPassword", cobCredentials.getPassword())
        };

        try {
            return httpClient.post(Constants.COBRAND_LOGIN_PATH, bodyParams, CobrandSession.class);
        } catch (Exception e) {
            throw new YodleeConnectorException(e);
        }
    }

    public UserSession userLogin(UserCredentials credentials) throws YodleeConnectorException {
        NameValuePair[] bodyParams = new NameValuePair[] {
                new BasicNameValuePair("login", credentials.getLogin()),
                new BasicNameValuePair("password", credentials.getPassword()),
                new BasicNameValuePair("cobSessionToken", credentials.getCobrandToken())
        };

        try {
            return httpClient.post(Constants.USER_LOGIN_PATH, bodyParams, UserSession.class);
        } catch (Exception e) {
            throw new YodleeConnectorException(e);
        }
    }

    public TransactionSearchResult transactionSearch(TransactionSearchRequest request) throws YodleeConnectorException {
        NameValuePair[] bodyParams = new NameValuePair[] {
                new BasicNameValuePair("cobSessionToken", request.getCobSessionToken()),
                new BasicNameValuePair("userSessionToken", request.getUserSessionToken()),

                new BasicNameValuePair("transactionSearchRequest.containerType",
                        request.getContainerType().getYName()),
                new BasicNameValuePair("transactionSearchRequest.searchFilter.transactionSplitType",
                        request.getTransactionSplitType().name()),
                new BasicNameValuePair("transactionSearchRequest.higherFetchLimit",
                        String.valueOf(request.getHigherFetchLimit())),
                new BasicNameValuePair("transactionSearchRequest.lowerFetchLimit",
                        String.valueOf(request.getLowerFetchLimit())),
                new BasicNameValuePair("transactionSearchRequest.resultRange.startNumber",
                        String.valueOf(request.getRangeStartNumber())),
                new BasicNameValuePair("transactionSearchRequest.resultRange.endNumber",
                        String.valueOf(request.getRangeEndNumber())),
                new BasicNameValuePair("transactionSearchRequest.searchClients.clientId",
                        String.valueOf(request.getSearchClientId())),
                new BasicNameValuePair("transactionSearchRequest.searchClients.clientName",
                        request.getSearchClientName()),
                new BasicNameValuePair("transactionSearchRequest.ignoreUserInput",
                        String.valueOf(request.isIgnoreUserInput()))
        };

        try {
            return httpClient.post(Constants.USER_TRANSACTION_SEARCH_PATH, bodyParams, TransactionSearchResult.class);
        } catch (Exception e) {
            throw new YodleeConnectorException(e);
        }
    }
}
