package org.jrivets.connector.yodlee.login;

/**
 * @author kbabushkin
 * @since 11/18/14
 */

public class UserSession {

    class UserContext {
        class ConversationCredentials {
            private String sessionToken;
        }
        private ConversationCredentials conversationCredentials = new ConversationCredentials();
    }

    private String loginName;
    private String userId;
    private UserContext userContext;

    UserSession() {
    }

    public String getLoginName() {
        return loginName;
    }

    public String getUserId() {
        return userId;
    }

    public String getSessionToken() {
        return userContext.conversationCredentials.sessionToken;
    }
}
