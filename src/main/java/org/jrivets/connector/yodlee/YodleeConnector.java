package org.jrivets.connector.yodlee;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jrivets.connector.util.JsonSerializer;
import org.jrivets.connector.yodlee.login.CobrandCredentials;
import org.jrivets.connector.yodlee.login.CobrandSession;
import org.jrivets.connector.yodlee.login.UserCredentials;
import org.jrivets.connector.yodlee.login.UserSession;
import org.jrivets.connector.yodlee.transaction.TransactionSearchRequest;
import org.jrivets.connector.yodlee.transaction.TransactionSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author kbabushkin
 * @since 11/17/14
 */

public class YodleeConnector {

    private final static Logger logger = LoggerFactory.getLogger(YodleeConnector.class);

    private final String baseURL;
    private final HttpClient httpClient;

    public YodleeConnector(ConnectorConfiguration configuration) {
        this.baseURL = configuration.getHostURL();
        this.httpClient = new HttpClientBuilder()
                .withConnectionTimeout(configuration.getConnectionTimeout())
                .withSocketReadTimeout(configuration.getSocketReadTimeout())
                .withMaxConnections(configuration.getMaxConnections())
                .build();
    }

    public CobrandSession cobrandLogin(CobrandCredentials cobCredentials) {
        NameValuePair[] bodyParams = new NameValuePair[]{
                new BasicNameValuePair("cobrandLogin", cobCredentials.getLogin()),
                new BasicNameValuePair("cobrandPassword", cobCredentials.getPassword())
        };

        return doPost(Constants.COBRAND_LOGIN_PATH, bodyParams, CobrandSession.class);
    }

    public UserSession userLogin(UserCredentials credentials) {
        NameValuePair[] bodyParams = new NameValuePair[]{
                new BasicNameValuePair("login", credentials.getLogin()),
                new BasicNameValuePair("password", credentials.getPassword()),
                new BasicNameValuePair("cobSessionToken", credentials.getCobrandToken())
        };

        return doPost(Constants.USER_LOGIN_PATH, bodyParams, UserSession.class);
    }

    public void userLogout(String cobSessionToken, String userSessionToken) {
        NameValuePair[] bodyParams = new NameValuePair[]{
                new BasicNameValuePair("cobSessionToken", cobSessionToken),
                new BasicNameValuePair("userSessionToken", userSessionToken)
        };

        doPost(Constants.USER_LOGOUT_PATH, bodyParams, null);
    }

    public TransactionSearchResult transactionSearch(TransactionSearchRequest request) {
        NameValuePair[] bodyParams = new NameValuePair[]{
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

        return doPost(Constants.USER_TRANSACTION_SEARCH_PATH, bodyParams, TransactionSearchResult.class);
    }

    <T> T doPost(String relativePath, NameValuePair[] bodyParams, Class<T> clazz) {
        try {
            Request request = Request.Post(buildRequestURL(relativePath)).bodyForm(bodyParams);
            HttpResponse httpResponse = Executor.newInstance(httpClient).execute(request).returnResponse();

            String bodyString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            if (clazz == null) {
                return null;
            }

            return JsonSerializer.fromJson(bodyString, clazz);
        } catch (Exception e) {
            logger.trace("Connector problem: ", e);
            throw new YodleeConnectorException(e);
        }
    }

    private String buildRequestURL(String path) throws URISyntaxException {
        return new URI(baseURL + path).toString();
    }
}
