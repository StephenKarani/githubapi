package com.stephenkarani.githubapi.activities.main;

import com.stephenkarani.githubapi.activities.responses.RepositoriesResponse;

import java.util.List;

public interface MainView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<RepositoriesResponse> repositoryList);
    void onErrorLoading(String message);
}
