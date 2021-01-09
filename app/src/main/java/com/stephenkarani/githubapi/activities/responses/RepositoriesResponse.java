package com.stephenkarani.githubapi.activities.responses;

import com.google.gson.annotations.SerializedName;
import com.stephenkarani.githubapi.activities.models.Owner;

public class RepositoriesResponse {
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
    @SerializedName("url")
    private String url;
    @SerializedName("description")
    private String description;
    @SerializedName("owner")
    private Owner owner;

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

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public Owner getOwner() {
        return owner;
    }
}
