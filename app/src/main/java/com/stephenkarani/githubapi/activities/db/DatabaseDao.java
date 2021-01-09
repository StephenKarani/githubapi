package com.stephenkarani.githubapi.activities.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DatabaseDao {
    @Insert
    Long insertFavourite(FavouriteRepository favouriteRepository);

    @Delete
    void deleteFavourite(FavouriteRepository favouriteRepository);

    @Query("SELECT * FROM tbl_favourites")
    List<FavouriteRepository> getFavouriteRepositories();
}
