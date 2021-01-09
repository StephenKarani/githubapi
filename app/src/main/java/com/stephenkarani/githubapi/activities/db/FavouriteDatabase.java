package com.stephenkarani.githubapi.activities.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FavouriteRepository.class}, version = 1, exportSchema = false)
public abstract class FavouriteDatabase extends RoomDatabase {
    public abstract DatabaseDao databaseDao();
}
