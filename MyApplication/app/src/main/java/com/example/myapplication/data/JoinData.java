package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("userName")
    private String userName;

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("userPwd")
    private String userPwd;

    @SerializedName("userPn")
    private String userPn;

    @SerializedName("userAd")
    private String userAd;

    public JoinData(String userName, String userEmail, String userPwd, String userPn, String userAd) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userPn = userPn;
        this.userAd = userAd;
    }
}
