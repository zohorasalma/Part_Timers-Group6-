package com.example.parttimers.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class AddPostActivity extends AppCompatActivity
{
    private EditText ed_title,ed_location,ed_description,ed_salary,ed_deadLine,ed_companyName,ed_mQ;
    private String title,location,description,companyName,minQ,salary,deadLine;
    private String url = "https://zohorasalmaisdproject.000webhostapp.com/AddPost.php";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        ed_title = findViewById(R.id.postTitle);
        ed_location = findViewById(R.id.postLocation);
        ed_deadLine = findViewById(R.id.postDeadLine);
        ed_description = findViewById(R.id.postDescription);
        ed_salary = findViewById(R.id.postSalary);
        ed_companyName = findViewById(R.id.postCompanyName);
        ed_mQ = findViewById(R.id.postMinimumQ);

        bottomNavigationView.setSelectedItemId(R.id.addPost);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.addPost:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), MyProfileActivity.class));
                        overridePendingTransition(0,0);
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

    public void SubmitPost(View view)
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");


        if(ed_title.getText().toString().equals("")){
            Toast.makeText(this, "Enter Job Title", Toast.LENGTH_SHORT).show();
        }
        else if(ed_location.getText().toString().equals("")){
            Toast.makeText(this, "Enter Job Location", Toast.LENGTH_SHORT).show();
        }
        else{

            progressDialog.show();
            title = ed_title.getText().toString().trim();
            location = ed_location.getText().toString().trim();
            description = ed_description.getText().toString().trim();
            salary = ed_salary.getText().toString().trim();
            deadLine = ed_deadLine.getText().toString().trim();
            companyName = ed_companyName.getText().toString().trim();
            minQ = ed_mQ.getText().toString().trim();



            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    ed_title.setText("");
                    ed_location.setText("");
                    ed_salary.setText("");
                    ed_description.setText("");
                    ed_deadLine.setText("");
                    ed_companyName.setText("");
                    ed_mQ.setText("");

                    Toast.makeText(AddPostActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(AddPostActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("title",title);
                    params.put("location",location);
                    params.put("description",description);
                    params.put("salary",salary);
                    params.put("deadline",deadLine);
                    params.put("companyName",companyName);
                    params.put("minQ",minQ);

                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(AddPostActivity.this);
            requestQueue.add(request);


        }
    }
}