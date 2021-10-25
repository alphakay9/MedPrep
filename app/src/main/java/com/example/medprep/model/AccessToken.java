package com.example.medprep.model;

import com.google.gson.annotations.SerializedName;


public class AccessToken {
    // Token string
    /// </summary>
    @SerializedName("Token")
    private String Token;

    /// <summary>
    /// Valid period of token in seconds
    /// </summary>
    @SerializedName("ValidThrough")
    private int ValidThrough;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getValidThrough() {
        return ValidThrough;
    }

    public void setValidThrough(int validThrough) {
        ValidThrough = validThrough;
    }

}
