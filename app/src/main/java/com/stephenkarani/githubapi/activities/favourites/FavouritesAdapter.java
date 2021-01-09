package com.stephenkarani.githubapi.activities.favourites;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stephenkarani.githubapi.R;
import com.stephenkarani.githubapi.activities.db.FavouriteRepository;
import com.stephenkarani.githubapi.activities.fdetails.FavouritesDetailActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder> {
    Context context;
    List<FavouriteRepository> repositoryList;

    public FavouritesAdapter(Context context, List<FavouriteRepository> repositoryList) {
        this.context = context;
        this.repositoryList = repositoryList;
    }

    @NonNull
    @Override
    public FavouritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favourite_repository, parent, false);
        return new FavouritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesViewHolder holder, int position) {
        FavouriteRepository repository = repositoryList.get(position);
        holder.bindRepositories(repository);
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    class FavouritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ConstraintLayout item_linear_layout;
        TextView item_name, item_type, item_html_url, item_description;

        public FavouritesViewHolder(@NonNull View itemView) {
            super(itemView);
            item_linear_layout = itemView.findViewById(R.id.fav_con_layout);
            item_name = itemView.findViewById(R.id.fav_name);
            item_type = itemView.findViewById(R.id.fav_type);
            item_html_url = itemView.findViewById(R.id.fav_html_url);
            item_description = itemView.findViewById(R.id.fav_description);

            item_linear_layout.setOnClickListener(this);
        }

        public void bindRepositories(FavouriteRepository repository) {
            item_name.setText(repository.getRepositoryName());
            item_type.setText(repository.getRepositoryType());
            item_html_url.setText(repository.getRepositoryUrl());
            item_description.setText(repository.getRepositoryDescription());
        }

        @Override
        public void onClick(View v) {
            String repositoryLogin = repositoryList.get(getAdapterPosition()).getRepositoryLogin();
            String repositoryName = repositoryList.get(getAdapterPosition()).getRepositoryName();

            Intent intent = new Intent(context, FavouritesDetailActivity.class);
            intent.putExtra("repoLogin", repositoryLogin);
            intent.putExtra("repoName", repositoryName);

            context.startActivity(intent);
        }
    }
}
