package com.example.parttimers.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parttimers.MainActivity;
import com.example.parttimers.R;
import com.example.parttimers.SignupActivity;
import com.example.parttimers.model.JobPost;
import com.example.parttimers.model.User;
import com.example.parttimers.utility.ApplicationData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;

public class MyProfileActivity extends AppCompatActivity
{
    private TextView userName,education,skill,interest;
    private ImageView editEducation, editSkill,editInterest;
    private String url = "https://zohorasalmaisdproject.000webhostapp.com/retriveUsers.php";
    private static List<User> UserList = new ArrayList<>();
    private User user;
    private EditText ed_education,ed_skill,ed_interest;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        getSupportActionBar().hide();
        retrieveData();
        final ProgressDialog progressDialog = new ProgressDialog(MyProfileActivity.this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.show();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        userName = findViewById(R.id.userNameTV);
        education = findViewById(R.id.education);
        skill = findViewById(R.id.skill);
        interest = findViewById(R.id.interest);
        editEducation = findViewById(R.id.educationEdit);
        editSkill = findViewById(R.id.skillEdit);
        editInterest = findViewById(R.id.interestEdit);
        ed_education = findViewById(R.id.ed_education);
        ed_skill = findViewById(R.id.ed_skill);
        ed_interest = findViewById(R.id.ed_interest);


        User userD = ApplicationData.userDetails;
        userName.setText(userD.getUser_name());
        education.setText(userD.getEducation());
        skill.setText(userD.getSkill());
        interest.setText(userD.getInterest());
        progressDialog.dismiss();

        editEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(ed_education.getVisibility()==View.INVISIBLE){
                    ed_education.setVisibility(View.VISIBLE);
                    ed_education.setText(ApplicationData.userDetails.getEducation());
                    education.setVisibility(View.INVISIBLE);
                }
                else{
                    ed_education.setVisibility(View.INVISIBLE);
                    education.setVisibility(View.VISIBLE);
                }

            }
        });
        editSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(ed_skill.getVisibility()==View.INVISIBLE){
                    ed_skill.setVisibility(View.VISIBLE);
                    ed_skill.setText(ApplicationData.userDetails.getSkill());
                    skill.setVisibility(View.INVISIBLE);
                }
                else{
                    ed_skill.setVisibility(View.INVISIBLE);
                    skill.setVisibility(View.VISIBLE);
                }
            }
        });
        editInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(ed_interest.getVisibility()==View.INVISIBLE){
                    ed_interest.setVisibility(View.VISIBLE);
                    ed_interest.setText(ApplicationData.userDetails.getInterest());
                    interest.setVisibility(View.INVISIBLE);
                }
                else{
                    ed_interest.setVisibility(View.INVISIBLE);
                    interest.setVisibility(View.VISIBLE);
                }
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.profile);
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
                        return true;
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    public void retrieveData()
    {
        final ProgressDialog progressDialog = new ProgressDialog(MyProfileActivity.this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(success.equals("1"))
                            {
                                for(int i = 0; i<jsonArray.length();i++)
                                {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    String id = jsonObject1.getString("id");
                                    String user_name = jsonObject1.getString("user_name");
                                    String user_email = jsonObject1.getString("user_email");
                                    String user_pass = jsonObject1.getString("user_pass");
                                    String education = jsonObject1.getString("education");
                                    String skill = jsonObject1.getString("skill");
                                    String interest = jsonObject1.getString("interest");

                                    user = new User(id,user_name,user_email,user_pass,education,skill,interest);
                                    UserList.add(user);

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
                Toast.makeText(MyProfileActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(MyProfileActivity.this);
        requestQueue.add(request);
        for( User user : UserList)
        {
            if(user.getUser_email().toLowerCase().contains(ApplicationData.user_email))
            {
               ApplicationData.userDetails = user;
            }
        }
        progressDialog.dismiss();
    }
    public void updateProfile(View v)
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating ....");
        progressDialog.show();
        final String educationstr = ed_education.getText().toString();
        final String skillstr = ed_skill.getText().toString();
        final String intereststr = ed_interest.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST,
                "https://zohorasalmaisdproject.000webhostapp.com/updateProfile.php",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(MyProfileActivity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                progressDialog.dismiss();
                Toast.makeText(MyProfileActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> params = new HashMap<String, String>();

                params.put("email",ApplicationData.user_email);
                params.put("education",educationstr);
                params.put("skill",skillstr);
                params.put("interest",intereststr);

                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MyProfileActivity.this);
        requestQueue.add(request);
    }
}