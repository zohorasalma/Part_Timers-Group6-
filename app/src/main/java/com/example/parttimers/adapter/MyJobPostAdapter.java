package com.example.parttimers.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.parttimers.R;
import com.example.parttimers.model.JobPost;
import com.example.parttimers.ui.ViewMoreActivity;
import com.example.parttimers.utility.ApplicationData;

import java.util.ArrayList;
import java.util.List;

public class MyJobPostAdapter extends RecyclerView.Adapter<MyJobPostAdapter.ViewHolder>
{
    Context context;
    private List<JobPost> jobPosts;

    // RecyclerView recyclerView;
    public MyJobPostAdapter(Context context,List<JobPost> jobPosts)
    {
        this.context = context;
        this.jobPosts = jobPosts;
    }

    @Override
    public ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.post_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(
            ViewHolder holder,
            int position)
    {
        final JobPost jobPost = jobPosts.get(position);
        holder.title.setText(jobPosts.get(position).getTitle());
        holder.location.setText(jobPosts.get(position).getLocation());
        holder.deadline.setText(jobPosts.get(position).getDeadline());
        holder.viewMoreBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, ViewMoreActivity.class);
                context.startActivity(intent);
                ApplicationData.jobPostDetails = jobPost;

            }
        });

    }


    @Override
    public int getItemCount()
    {
        return jobPosts.size();
    }

    public void filterList(ArrayList<JobPost> filteredList)
    {
        jobPosts = filteredList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title,location,deadline;
        public RelativeLayout relativeLayout;
        public Button viewMoreBtn;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.title =  itemView.findViewById(R.id.txtviewTitle);
            this.location = (TextView) itemView.findViewById(R.id.txtviewLocation);
            this.deadline = itemView.findViewById(R.id.txtviewDeadline);
            this.viewMoreBtn = itemView.findViewById(R.id.viewMoreBtn);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}
