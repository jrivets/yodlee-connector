package org.jrivets.connector.yodlee;

import com.google.gson.annotations.SerializedName;

/**
 * @author kbabushkin
 * @since 11/18/14
 */

public class CobrandCredentials {

    @SerializedName("cobrandLogin")
    private String login;

    @SerializedName("cobrandPassword")
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
