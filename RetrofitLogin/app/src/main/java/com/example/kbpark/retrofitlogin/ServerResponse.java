package com.example.kbpark.retrofitlogin;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by KBPark on 2017. 1. 26..
 */


public class ServerResponse implements Serializable {
    @SerializedName("returned_username")
    private String username;
    @SerializedName("returned_password")
    private String password;
    @SerializedName("message")
    private String message;
    @SerializedName("response_code")
    private int responseCode;

    public ServerResponse(String username, String password, String message, int responseCode){
        this.username = username;
        this.password = password;
        this.message = message;
        this.responseCode = responseCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
