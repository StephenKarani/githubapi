package com.stephenkarani.githubapi.activities.favourites;

import com.stephenkarani.githubapi.activities.db.FavouriteRepository;

import java.util.List;

public interface FavouritesView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<FavouriteRepository> repositoryList);
    void onErrorLoading(String message);
}
