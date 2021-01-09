package com.stephenkarani.githubapi.activities.main;

import com.stephenkarani.githubapi.activities.api.ApiClient;
import com.stephenkarani.githubapi.activities.api.GithubApiInterface;
import com.stephenkarani.githubapi.activities.responses.RepositoriesResponse;
import com.stephenkarani.githubapi.activities.responses.RepositoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {
    MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    void getRepositories() {
        view.showLoading();

        GithubApiInterface apiInterface = ApiClient.getApiClient().create(GithubApiInterface.class);
        Call<List<RepositoriesResponse>> call = apiInterface.getRepositories();
        call.enqueue(new Callback<List<RepositoriesResponse>>() {
            @Override
            public void onResponse(Call<List<RepositoriesResponse>> call, Response<List<RepositoriesResponse>> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<RepositoriesResponse>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }
}
