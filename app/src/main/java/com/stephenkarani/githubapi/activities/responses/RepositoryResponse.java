package com.stephenkarani.githubapi.activities.responses;

import com.google.gson.annotations.SerializedName;
import com.stephenkarani.githubapi.activities.models.Owner;

public class RepositoryResponse {
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

    public Owner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "RepositoryResponse{" +
                "id=" + id +
                ", node_id='" + node_id + '\'' +
                ", name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", private_repo=" + private_repo +
                ", html_url='" + html_url + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", stargazers_count=" + stargazers_count +
                ", watchers_count=" + watchers_count +
                ", language='" + language + '\'' +
                ", owner=" + owner +
                '}';
    }
}
