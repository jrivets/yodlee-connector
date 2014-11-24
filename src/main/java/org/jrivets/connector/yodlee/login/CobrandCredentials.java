package org.jrivets.connector.yodlee.login;

/**
 * @author kbabushkin
 * @since 11/18/14
 */

public class CobrandCredentials {

    private String login;
    private String password;

    public CobrandCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
