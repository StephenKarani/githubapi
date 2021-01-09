package com.stephenkarani.githubapi.activities.detail;

import com.stephenkarani.githubapi.activities.responses.RepositoryResponse;

public interface DetailView {
    void showLoading();
    void hideLoading();
    void onGetResult(RepositoryResponse repositoryDetails);
    void onErrorLoading(String message);
}
