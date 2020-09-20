package com.example.parttimers.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.parttimers.R;
import com.example.parttimers.model.JobPost;

import java.util.List;

public class MyJobPostAdapter extends RecyclerView.Adapter<MyJobPostAdapter.ViewHolder>
{
    private List<JobPost> jobPosts;

    // RecyclerView recyclerView;
    public MyJobPostAdapter(List<JobPost> jobPosts)
    {
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
        holder.textView.setText(jobPosts.get(position).getTitle());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(view.getContext(), "click on item: " + jobPost.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount()
    {
        return jobPosts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}
