package com.stephenkarani.githubapi.activities.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.stephenkarani.githubapi.R;
import com.stephenkarani.githubapi.activities.db.DatabaseRepository;
import com.stephenkarani.githubapi.activities.db.FavouriteDatabase;
import com.stephenkarani.githubapi.activities.db.FavouriteRepository;
import com.stephenkarani.githubapi.activities.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new SplashExecution().execute();
    }

    class SplashExecution extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }
}