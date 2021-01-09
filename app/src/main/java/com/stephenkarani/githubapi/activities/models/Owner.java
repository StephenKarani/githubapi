package com.stephenkarani.githubapi.activities.models;

import com.google.gson.annotations.SerializedName;

public class Owner {
    @SerializedName("id")
    private float id;
    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatar_url;

    public float getId() {
        return id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getLogin() {
        return login;
    }
}
