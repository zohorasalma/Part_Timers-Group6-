package com.example.parttimers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class StartPageActivity extends AppCompatActivity {

    TextView loglinktextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        getSupportActionBar().hide(); //hide the title bar

        Button signlinkbutton = (Button) findViewById(R.id.signUpLinkButton);
        loglinktextview = (TextView) findViewById(R.id.loginLinkTextView);

        signlinkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signintent = new Intent(StartPageActivity.this, SignupActivity.class);
                startActivity(signintent);
            }
        });

    }


    public void onLoginClick(View view)
    {
        Intent logintent = new Intent(StartPageActivity.this, LoginActivity.class);
        startActivity(logintent);
    }
}