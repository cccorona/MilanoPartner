package knowhere.mx.milanopartner.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by cacorona on 13/07/2016.
 */
public class MilanoUser implements Serializable {

    public static int USER_LOGED_TRUE=1;
    public static int USER_LOGED_FALSE=0;


    private transient int isLogged;
    @SerializedName("username")
    private String userName;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    private String key;

    public MilanoUser(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public int getIsLogged() {
        return isLogged;
    }

    public void setIsLogged(int isLogged) {
        this.isLogged = isLogged;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return key;
    }

    public void setToken(String token) {
        this.key = token;
    }
}
