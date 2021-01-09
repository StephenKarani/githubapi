package com.stephenkarani.githubapi.activities.db;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import androidx.room.Room;

public class DatabaseRepository {
    String dbName = "gitdb";
    FavouriteDatabase favouriteDatabase;
    Context context;

    public DatabaseRepository(Context context) {
        this.context = context;
        favouriteDatabase = Room.databaseBuilder(context, FavouriteDatabase.class, dbName).allowMainThreadQueries().build();
    }

    public void insertFavouriteRepository(final FavouriteRepository favouriteRepository) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                favouriteDatabase.databaseDao().insertFavourite(favouriteRepository);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context, favouriteRepository.repositoryName + " has been added", Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

    public List<FavouriteRepository> getFavouriteRepository() {
        List<FavouriteRepository> repositoryList = favouriteDatabase.databaseDao().getFavouriteRepositories();
        return repositoryList;
    }

    public void deleteFavouriteRepository(final FavouriteRepository favouriteRepository) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                favouriteDatabase.databaseDao().deleteFavourite(favouriteRepository);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context, favouriteRepository.repositoryName + " has been removed", Toast.LENGTH_LONG).show();
            }
        }.execute();
    }
}
