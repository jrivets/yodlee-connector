package org.jrivets.connector.yodlee.login;

/**
 * @author kbabushkin
 * @since 11/18/14
 */

public class UserCredentials {

    private String login;
    private String password;
    private String cobrandToken;

    public UserCredentials(String login, String password, String cobrandToken) {
        this.login = login;
        this.password = password;
        this.cobrandToken = cobrandToken;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getCobrandToken() {
        return cobrandToken;
    }
}
