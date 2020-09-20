package com.example.parttimers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parttimers.adapter.MyJobPostAdapter;
import com.example.parttimers.model.JobPost;
import com.example.parttimers.ui.AddPostActivity;
import com.example.parttimers.ui.MyProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<JobPost> jobPostList = new ArrayList<>();
    private String url = "https://zohorasalmaisdproject.000webhostapp.com/retrievePosts.php";
    private JobPost jobPost;
    private MyJobPostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.addPost:
                        startActivity(new Intent(getApplicationContext(), AddPostActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), MyProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.dashboard:
                        return true;
                }
                return false;
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPosts);
        adapter = new MyJobPostAdapter(jobPostList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        retrieveData();
    }
    public void retrieveData()
    {
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        jobPostList.clear();
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(success.equals("1"))
                            {
                                for(int i = 0; i<jsonArray.length();i++)
                                {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    String id = jsonObject1.getString("postId");
                                    String title = jsonObject1.getString("title");
                                    String location = jsonObject1.getString("location");
                                    String description = jsonObject1.getString("description");
                                    String salary = jsonObject1.getString("salary");
                                    String deadline = jsonObject1.getString("deadline");
                                    String companyName = jsonObject1.getString("companyName");
                                    String minimumQual = jsonObject1.getString("minimumQual");

                                    jobPost = new JobPost(id,title,location,description,salary,deadline,companyName,minimumQual);
                                    jobPostList.add(jobPost);
                                    adapter.notifyDataSetChanged();


                                }
                            }

                        }catch (JSONException e)
                        {

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }
}