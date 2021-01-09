package com.stephenkarani.githubapi.activities.main;

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
import com.stephenkarani.githubapi.activities.detail.DetailActivity;
import com.stephenkarani.githubapi.activities.favourites.FavouritesActivity;
import com.stephenkarani.githubapi.activities.responses.RepositoriesResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private static final int INTENT_VIEW = 100;
    private static final int INTENT_FAVOURITES = 200;
    private static final String TAG = "MainActivity";

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    ImageView favourites_button;

    MainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListener itemClickListener;

    List<RepositoriesResponse> repositoryList;
    boolean backClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        favourites_button = findViewById(R.id.favourites_button);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new MainPresenter(this);
        presenter.getRepositories();

        swipeRefreshLayout.setOnRefreshListener(() -> presenter.getRepositories());
        itemClickListener = (view, position) -> {

            String repositoryLogin = repositoryList.get(position).getOwner().getLogin();
            String repositoryName = repositoryList.get(position).getName();

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("repoLogin", repositoryLogin);
            intent.putExtra("repoName", repositoryName);
            startActivityForResult(intent, INTENT_VIEW);
        };
        favourites_button.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == INTENT_VIEW && resultCode == RESULT_OK){
            swipeRefreshLayout.setRefreshing(true);
            presenter.getRepositories();
        } else if (requestCode == INTENT_FAVOURITES && resultCode == RESULT_OK){
            swipeRefreshLayout.setRefreshing(true);
            presenter.getRepositories();
        }
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
    public void onGetResult(List<RepositoriesResponse> repositoryList) {
        adapter = new MainAdapter(this, repositoryList, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        this.repositoryList = repositoryList;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_SHORT).show();
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
    public void onClick(View v) {
        if (v == favourites_button) {
            Intent intent = new Intent(MainActivity.this, FavouritesActivity.class);
            startActivityForResult(intent, INTENT_FAVOURITES);
        }
    }
}