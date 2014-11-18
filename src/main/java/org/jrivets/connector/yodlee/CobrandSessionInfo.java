package org.jrivets.connector.yodlee;

/**
 * @author kbabushkin
 * @since 11/18/14
 */

public class CobrandSessionInfo {

    public class ConversationCredentials {
        private String sessionToken;

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

    public ConversationCredentials getCobrandConversationCredentials() {
        return cobrandConversationCredentials;
    }
}
