package com.example.parttimers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.parttimers.utility.ApplicationData;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    private EditText ed_username,ed_email,ed_password;
    private String str_name,str_email,str_password;
    private String url = "https://zohorasalmaisdproject.000webhostapp.com/register.php";
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        ed_email = findViewById(R.id.emailSignup);
        ed_username = findViewById(R.id.usernameSignup);
        ed_password = findViewById(R.id.passwordSignup);
        signUpButton = findViewById(R.id.signupButton);


        Button signbackbutton = (Button) findViewById(R.id.signupBackButton);

        signbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signbackintent = new Intent(SignupActivity.this, StartPageActivity.class);
                startActivity(signbackintent);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Register(view);
            }
        });
    }
    public void Register(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");


        if(ed_username.getText().toString().equals("")){
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        }
        else if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{

            progressDialog.show();
            str_name = ed_username.getText().toString().trim();
            str_email = ed_email.getText().toString().trim();
            str_password = ed_password.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    ed_username.setText("");
                    ed_email.setText("");
                    ed_password.setText("");
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    ApplicationData.user_email= str_email;
                    Toast.makeText(SignupActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(SignupActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("user_name",str_name);
                    params.put("user_email",str_email);
                    params.put("user_pass",str_password);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(SignupActivity.this);
            requestQueue.add(request);

        }

    }
}