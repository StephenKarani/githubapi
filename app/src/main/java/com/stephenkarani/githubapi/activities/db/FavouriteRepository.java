package com.stephenkarani.githubapi.activities.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "tbl_favourites",
        indices = {@Index(name = "index_repo_id", value = "repo_id", unique = true)}
)
public class FavouriteRepository {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "repo_id")
    int repositoryID;
    @ColumnInfo(name = "repo_name")
    String repositoryName;
    @ColumnInfo(name = "repo_type")
    String repositoryType;
    @ColumnInfo(name = "repo_url")
    String repositoryUrl;
    @ColumnInfo(name = "repo_description")
    String repositoryDescription;
    @ColumnInfo(name = "repo_login")
    String repositoryLogin;

    @Ignore
    public FavouriteRepository(String repositoryName, String repositoryType, String repositoryUrl, String repositoryDescription, String repositoryLogin) {
        this.repositoryName = repositoryName;
        this.repositoryType = repositoryType;
        this.repositoryUrl = repositoryUrl;
        this.repositoryDescription = repositoryDescription;
        this.repositoryLogin = repositoryLogin;
    }

    public FavouriteRepository(int repositoryID, String repositoryName, String repositoryType, String repositoryUrl, String repositoryDescription, String repositoryLogin) {
        this.repositoryID = repositoryID;
        this.repositoryName = repositoryName;
        this.repositoryType = repositoryType;
        this.repositoryUrl = repositoryUrl;
        this.repositoryDescription = repositoryDescription;
        this.repositoryLogin = repositoryLogin;
    }

    public int getRepositoryID() {
        return repositoryID;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getRepositoryType() {
        return repositoryType;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public String getRepositoryDescription() {
        return repositoryDescription;
    }

    public String getRepositoryLogin() {
        return repositoryLogin;
    }
}
