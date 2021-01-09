package com.stephenkarani.githubapi.activities.favourites;

import android.content.Context;

import com.stephenkarani.githubapi.activities.db.DatabaseRepository;
import com.stephenkarani.githubapi.activities.db.FavouriteRepository;

import java.util.List;

public class FavouritesPresenter {
    FavouritesView view;
    DatabaseRepository databaseRepository;
    List<FavouriteRepository> repositoryList;

    public FavouritesPresenter(FavouritesView view) {
        this.view = view;
    }

    void getAllFavouriteRepositories(Context context) {
        view.showLoading();
        try {
            view.hideLoading();
            databaseRepository = new DatabaseRepository(context);
            repositoryList = databaseRepository.getFavouriteRepository();
            view.onGetResult(repositoryList);
        } catch (Exception e) {
            view.hideLoading();
            view.onErrorLoading(e.getMessage());
        }
    }

    void deleteFavouriteRepository(Context context, FavouriteRepository favouriteRepository) {
        try {
            databaseRepository = new DatabaseRepository(context);
            databaseRepository.deleteFavouriteRepository(favouriteRepository);
        } catch (Exception e) {
            view.hideLoading();
            view.onErrorLoading(e.getMessage());
        }
    }
}
