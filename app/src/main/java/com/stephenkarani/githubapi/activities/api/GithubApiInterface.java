package com.stephenkarani.githubapi.activities.api;

import com.stephenkarani.githubapi.activities.responses.RepositoriesResponse;
import com.stephenkarani.githubapi.activities.responses.RepositoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApiInterface {
    /*GET REPOS*/
    @GET("repositories")
    Call<List<RepositoriesResponse>> getRepositories();

    @GET("repos/{login}/{name}")
    Call<RepositoryResponse> getRepositoryDetails(@Path("login") String login, @Path("name") String name);
}
