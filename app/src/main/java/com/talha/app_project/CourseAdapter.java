package com.talha.app_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.VH> {

    private final List<Course> list;

    public CourseAdapter(List<Course> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Course c = list.get(position);
        h.ivThumb.setImageResource(c.imageRes);
        h.tvTitle.setText(c.title);
        h.tvAuthor.setText(c.author);
        h.ratingBar.setRating(c.rating);
        h.tvProgress.setText(c.progress + "% Done");
        h.progressBar.setProgress(c.progress);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView tvTitle, tvAuthor, tvProgress;
        RatingBar ratingBar;
        LinearProgressIndicator progressBar;

        VH(@NonNull View itemView) {
            super(itemView);
            ivThumb = itemView.findViewById(R.id.ivThumb);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvProgress = itemView.findViewById(R.id.tvProgress);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
