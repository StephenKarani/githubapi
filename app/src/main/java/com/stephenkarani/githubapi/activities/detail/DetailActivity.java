package com.stephenkarani.githubapi.activities.detail;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.stephenkarani.githubapi.R;
import com.stephenkarani.githubapi.activities.db.FavouriteRepository;
import com.stephenkarani.githubapi.activities.main.MainActivity;
import com.stephenkarani.githubapi.activities.responses.RepositoryResponse;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailView, View.OnClickListener {

    private static final String TAG = "DetailActivity";
    boolean backClicked = false;
    ProgressBar progressBar;
    CircleImageView repository_image;
    ConstraintLayout body_layout;
    ImageView back_button;
    Button add_to_favourites;
    TextView repository_name, repository_type, repository_full_name, repository_language, repository_description,
             repository_url, repository_star_gazers, repository_watchers, repository_size;
    String repositoryName, repositoryFullName, repositoryDescription, repositoryType, repositoryLanguage, repositoryImage,
           repositoryUrl, repositoryStarGazers, repositoryWatchers, repositorySize, repositoryLogin;
    String repoName, repoLogin;

    DetailPresenter presenter;
    RepositoryResponse repositoryDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progress_bar);
        back_button = findViewById(R.id.back_button);
        body_layout = findViewById(R.id.body_layout);
        add_to_favourites = findViewById(R.id.add_to_favourites);
        repository_image = findViewById(R.id.repository_image);
        repository_name = findViewById(R.id.repository_name);
        repository_type = findViewById(R.id.repository_type);
        repository_full_name = findViewById(R.id.repository_full_name);
        repository_language = findViewById(R.id.repository_language);
        repository_description = findViewById(R.id.repository_description);
        repository_url = findViewById(R.id.repository_url);
        repository_star_gazers = findViewById(R.id.repository_star_gazers);
        repository_watchers = findViewById(R.id.repository_watchers);
        repository_size = findViewById(R.id.repository_size);

        getRepositoryData();

        presenter = new DetailPresenter(this);
        presenter.getRepositoryDetails(repoLogin, repoName);

        back_button.setOnClickListener(this);
        add_to_favourites.setOnClickListener(this);
    }

    void setRepositoryData() {
        Glide.with(this).load(repositoryImage).into(repository_image);
        repository_name.setText(repositoryName);
        repository_type.setText(repositoryType);
        repository_full_name.setText(repositoryFullName);
        repository_language.setText(repositoryLanguage);
        repository_description.setText(repositoryDescription);
        repository_url.setText(repositoryUrl);
        repository_star_gazers.setText(repositoryStarGazers);
        repository_watchers.setText(repositoryWatchers);
        repository_size.setText(repositorySize);
    }

    void getRepositoryData() {
        Intent intent = getIntent();
        repoLogin = intent.getStringExtra("repoLogin");
        repoName = intent.getStringExtra("repoName");
    }

    void bindRepositoryData() {
        repositoryName = repositoryDetails.getName();
        repositoryFullName = repositoryDetails.getFull_name();
        repositoryType = repositoryDetails.isPrivate_repo() ? "Private" : "Public";
        repositoryLanguage = repositoryDetails.getLanguage();
        repositoryDescription = repositoryDetails.getDescription();
        repositoryImage = repositoryDetails.getOwner().getAvatar_url();
        repositoryUrl = repositoryDetails.getHtml_url();
        repositoryStarGazers = String.valueOf(repositoryDetails.getStargazers_count());
        repositoryWatchers = String.valueOf(repositoryDetails.getWatchers_count());
        repositorySize = String.valueOf(repositoryDetails.getSize());
        repositoryLogin = repositoryDetails.getOwner().getLogin();
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
        progressBar.setVisibility(View.VISIBLE);
        body_layout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        body_layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetResult(RepositoryResponse repositoryDetails) {
        this.repositoryDetails = repositoryDetails;
        bindRepositoryData();
        setRepositoryData();
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v == back_button) {
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
            setResult(RESULT_OK);
            startActivity(intent);
            finish();
        } else if (v == add_to_favourites) {
            FavouriteRepository favouriteRepository = new FavouriteRepository(repositoryName, repositoryType, repositoryUrl, repositoryDescription, repositoryLogin);
            presenter.addFavouriteRepository(DetailActivity.this, favouriteRepository);
        }
    }
}