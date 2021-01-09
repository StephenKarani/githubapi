package com.stephenkarani.githubapi.activities.favourites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.stephenkarani.githubapi.R;
import com.stephenkarani.githubapi.activities.db.FavouriteRepository;
import com.stephenkarani.githubapi.activities.main.MainActivity;

import java.util.List;

public class FavouritesActivity extends AppCompatActivity implements FavouritesView, View.OnClickListener {

    private static final String TAG = "FavouritesActivity";

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    ImageView back_button;

    FavouritesPresenter presenter;
    FavouritesAdapter adapter;

    List<FavouriteRepository> repositoryList;
    boolean backClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        back_button = findViewById(R.id.back_button);
        recyclerView = findViewById(R.id.fav_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new FavouritesPresenter(this);
        presenter.getAllFavouriteRepositories(FavouritesActivity.this);

        swipeRefreshLayout.setOnRefreshListener(() -> presenter.getAllFavouriteRepositories(FavouritesActivity.this));

        back_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == back_button) {
            Intent intent = new Intent(FavouritesActivity.this, MainActivity.class);
            setResult(RESULT_OK);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (backClicked) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }

        Toast.makeText(this, "Please press back again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backClicked = false;
                setResult(RESULT_OK);
                finish();
            }
        }, 3000);
        backClicked = true;
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<FavouriteRepository> repositoryList) {
        adapter = new FavouritesAdapter(FavouritesActivity.this, repositoryList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        this.repositoryList = repositoryList;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_SHORT).show();
    }
}