package com.stephenkarani.githubapi.activities.fdetails;

import com.stephenkarani.githubapi.activities.api.ApiClient;
import com.stephenkarani.githubapi.activities.api.GithubApiInterface;
import com.stephenkarani.githubapi.activities.responses.RepositoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouritesDetailPresenter {
    FavouritesDetailView view;

    public FavouritesDetailPresenter(FavouritesDetailView view) {
        this.view = view;
    }

    void getRepositoryDetails(String login, String name) {
        view.showLoading();

        GithubApiInterface apiInterface = ApiClient.getApiClient().create(GithubApiInterface.class);
        Call<RepositoryResponse> call = apiInterface.getRepositoryDetails(login, name);
        call.enqueue(new Callback<RepositoryResponse>() {
            @Override
            public void onResponse(Call<RepositoryResponse> call, Response<RepositoryResponse> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<RepositoryResponse> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
