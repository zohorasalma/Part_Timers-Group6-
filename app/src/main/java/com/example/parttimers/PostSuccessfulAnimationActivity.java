package com.example.parttimers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.parttimers.ui.AddPostActivity;

public class PostSuccessfulAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_successful_animation);

        Button animeBackButton = (Button) findViewById(R.id.animationBackButton);
        animeBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent animationBackButtonintent = new Intent(PostSuccessfulAnimationActivity.this, MainActivity.class);
                startActivity(animationBackButtonintent);
            }
        });
    }
}