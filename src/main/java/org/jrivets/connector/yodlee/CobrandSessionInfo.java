package org.jrivets.connector.yodlee;

/**
 * @author kbabushkin
 * @since 11/18/14
 */

public class CobrandSessionInfo {

    class ConversationCredentials {
        private String sessionToken;

        ConversationCredentials() {
        }

        public String getSessionToken() {
            return sessionToken;
        }
    }

    private String cobrandId;
    private String applicationId;
    private ConversationCredentials cobrandConversationCredentials;

    CobrandSessionInfo() {
    }

    public String getCobrandId() {
        return cobrandId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getSessionToken() {
        return cobrandConversationCredentials.getSessionToken();
    }
}
