package com.secpickup.android_front.modele;


import com.google.gson.annotations.SerializedName;

public class User extends BaseEntity {

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    @SerializedName("type")
    private UserAccountType type;

    public User(String username, String password, UserAccountType type) {
        this.username = username;
        this.password = password;
        this.type = type;
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

    public UserAccountType getType() {
        return type;
    }

    public void setType(UserAccountType type) {
        this.type = type;
    }
}

