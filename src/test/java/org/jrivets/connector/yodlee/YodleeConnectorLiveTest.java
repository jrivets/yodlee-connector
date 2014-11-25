package org.jrivets.connector.yodlee;

import org.jrivets.connector.yodlee.login.CobrandCredentials;
import org.jrivets.connector.yodlee.login.CobrandSession;
import org.jrivets.connector.yodlee.login.UserCredentials;
import org.jrivets.connector.yodlee.login.UserSession;
import org.jrivets.connector.yodlee.transaction.TransactionContainerType;
import org.jrivets.connector.yodlee.transaction.TransactionSearchRequest;
import org.jrivets.connector.yodlee.transaction.TransactionSearchResult;
import org.jrivets.connector.yodlee.transaction.TransactionSplitType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * @author kbabushkin
 * @since 11/17/14
 */

public class YodleeConnectorLiveTest {

    private static final String BASE_URL = "https://rest.developer.yodlee.com/services/srest/restserver/v1.0";

    private static final String COBRAND_LOGIN = "sbCobkbabushkin";
    private static final String COBRAND_PASSWD = "59c35270-b0fe-4ee8-b96a-9300ac17ee71";

    private static final String USER_LOGIN = "sbMemkbabushkin1";
    private static final String USER_PASSWD = "sbMemkbabushkin1#123";

    private YodleeConnector connector;

    private CobrandSession cobrandSession;
    private UserSession userSession;

    @BeforeTest
    public void init() throws YodleeConnectorException {
        connector = new YodleeConnector(new ConnectorConfiguration(BASE_URL));

        cobrandSession = cobrandLogin(COBRAND_LOGIN, COBRAND_PASSWD);
        userSession = userLogin(USER_LOGIN, USER_PASSWD, cobrandSession.getSessionToken());
    }

    @AfterTest
    public void tearDown() {
        connector.userLogout(cobrandSession.getSessionToken(), userSession.getSessionToken());
    }

    @Test
    public void testTransactionSearch() throws YodleeConnectorException {
        TransactionSearchRequest request =
                new TransactionSearchRequest(cobrandSession.getSessionToken(), userSession.getSessionToken());

        request.withContainerType(TransactionContainerType.ALL)
                .withTransactionSplitType(TransactionSplitType.ALL_TRANSACTION)
                .withHigherFetchLimit(500).withLowerFetchLimit(1)
                .withRangeStartNumber(1).withRangeEndNumber(100)
                .withSearchClientId(1).withSearchClientName("test")
                .withIgnoreUserInput(true);

        TransactionSearchResult result = connector.transactionSearch(request);
        assertNotNull(result.getSearchIdentifier());
    }

    private CobrandSession cobrandLogin(String login, String password) throws YodleeConnectorException {
        CobrandSession cobrandSession = connector.cobrandLogin(new CobrandCredentials(login, password));

        assertNotNull(cobrandSession.getApplicationId());
        assertNotNull(cobrandSession.getCobrandId());
        assertNotNull(cobrandSession.getSessionToken());

        return cobrandSession;
    }

    private UserSession userLogin(String login, String password, String cobSession) throws YodleeConnectorException {
        UserSession userSession = connector.userLogin(new UserCredentials(login, password, cobSession));

        assertNotNull(userSession.getLoginName());
        assertNotNull(userSession.getUserId());
        assertNotNull(userSession.getSessionToken());

        return userSession;
    }
}
