package com.stephenkarani.githubapi.activities.models;

import com.google.gson.annotations.SerializedName;

public class Repositories {
    @SerializedName("id")
    private float id;
    @SerializedName("node_id")
    private String node_id;
    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String full_name;
    @SerializedName("private")
    private boolean private_repo;
    @SerializedName("html_url")
    private String html_url;
    @SerializedName("description")
    private String description;
    @SerializedName("size")
    private float size;
    @SerializedName("stargazers_count")
    private float stargazers_count;
    @SerializedName("watchers_count")
    private float watchers_count;
    @SerializedName("language")
    private String language;

    public float getId() {
        return id;
    }

    public String getNode_id() {
        return node_id;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public boolean isPrivate_repo() {
        return private_repo;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getDescription() {
        return description;
    }

    public float getSize() {
        return size;
    }

    public float getStargazers_count() {
        return stargazers_count;
    }

    public float getWatchers_count() {
        return watchers_count;
    }

    public String getLanguage() {
        return language;
    }
}
