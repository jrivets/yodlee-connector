package org.jrivets.connector.yodlee;

import com.google.gson.annotations.SerializedName;

/**
 * @author kbabushkin
 * @since 11/18/14
 */

public class UserCredentials {

    @SerializedName("login")
    private String login;

    @SerializedName("password")
    private String password;

    @SerializedName("cobSessionToken")
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
