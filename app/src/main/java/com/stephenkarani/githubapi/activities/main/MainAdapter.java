package com.stephenkarani.githubapi.activities.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stephenkarani.githubapi.R;
import com.stephenkarani.githubapi.activities.responses.RepositoriesResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    Context context;
    List<RepositoriesResponse> repositoryList;
    ItemClickListener itemClickListener;

    public MainAdapter(Context context, List<RepositoriesResponse> repositoryList, ItemClickListener itemClickListener) {
        this.context = context;
        this.repositoryList = repositoryList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_repository, parent, false);
        return new MainViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        RepositoriesResponse repository = repositoryList.get(position);
        holder.bindRepositories(repository);
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ConstraintLayout item_linear_layout;
        TextView item_name, item_type, item_html_url, item_description;
        ItemClickListener itemClickListener;

        public MainViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            item_linear_layout = itemView.findViewById(R.id.item_linear_layout);
            item_name = itemView.findViewById(R.id.item_name);
            item_type = itemView.findViewById(R.id.item_type);
            item_html_url = itemView.findViewById(R.id.item_html_url);
            item_description = itemView.findViewById(R.id.item_description);

            this.itemClickListener = itemClickListener;
            item_linear_layout.setOnClickListener(this);
        }

        public void bindRepositories(RepositoriesResponse repository) {
            item_name.setText(repository.getName());
            item_type.setText(repository.isPrivate_repo() ? "Private" : "Public");
            item_html_url.setText(repository.getHtml_url());
            item_description.setText(repository.getDescription());
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
