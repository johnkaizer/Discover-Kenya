package com.project.elimu.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.elimu.ItemActivity;
import com.project.elimu.MainActivity;
import com.project.elimu.Model.PopularSchool;
import com.project.elimu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularSchoolAdapter extends RecyclerView.Adapter<PopularSchoolAdapter.PopularSchoolViewHolder>{
    private Context mContext;
    private List<PopularSchool> mPopularSchool;


    public PopularSchoolAdapter(Context context, List<PopularSchool> popularSchools) {
        mContext = context;
        mPopularSchool = popularSchools;
    }

    @Override
    public PopularSchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.popular_school_row_item, parent, false);
        PopularSchoolViewHolder holder =new PopularSchoolViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(PopularSchoolViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        PopularSchool uploadCurrent = mPopularSchool.get(position);
        holder.title.setText(uploadCurrent.getTitle());
        holder.status.setText(uploadCurrent.getStatus());
        Picasso.get().load(uploadCurrent.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ItemActivity.class);
                intent.putExtra("schoolImage",mPopularSchool.get(position).getImageUrl());
                intent.putExtra("title",mPopularSchool.get(position).getTitle());
                intent.putExtra("status",mPopularSchool.get(position).getStatus());
                intent.putExtra("location",mPopularSchool.get(position).getLocation());
                intent.putExtra("description",mPopularSchool.get(position).getDescription());
                intent.putExtra("population",mPopularSchool.get(position).getPopulation());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mPopularSchool.size();
    }

    public static final class PopularSchoolViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title, status;

        public PopularSchoolViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.popular_image);
            title = itemView.findViewById(R.id.school_title);
            status = itemView.findViewById(R.id.status);
        }
    }

}
