package org.jrivets.connector.yodlee.login;

/**
 * @author kbabushkin
 * @since 11/18/14
 */

public class CobrandSession {

    class ConversationCredentials {
        private String sessionToken;
    }

    private String cobrandId;
    private String applicationId;
    private ConversationCredentials cobrandConversationCredentials = new ConversationCredentials();

    CobrandSession() {
    }

    public String getCobrandId() {
        return cobrandId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getSessionToken() {
        return cobrandConversationCredentials.sessionToken;
    }
}
