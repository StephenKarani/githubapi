package com.stephenkarani.githubapi.activities.fdetails;

import com.stephenkarani.githubapi.activities.responses.RepositoryResponse;

public interface FavouritesDetailView {
    void showLoading();
    void hideLoading();
    void onGetResult(RepositoryResponse repositoryDetails);
    void onErrorLoading(String message);
}
