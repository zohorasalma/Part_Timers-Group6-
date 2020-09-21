package com.example.parttimers.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.parttimers.R;
import com.example.parttimers.model.JobPost;
import com.example.parttimers.utility.ApplicationData;

public class ViewMoreActivity extends AppCompatActivity
{
    private JobPost jobPost;
    private TextView title,location,description,salary,deadline,contactName,contactPhone,contactEmail,minQ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);
        getSupportActionBar().hide();

        if(ApplicationData.jobPostDetails!= null)
        {
            jobPost = ApplicationData.jobPostDetails;
        }
        title = findViewById(R.id.txtviewTitle);
        location = findViewById(R.id.txtviewLocation);
        description = findViewById(R.id.txtviewDescription);
        salary = findViewById(R.id.txtviewSalary);
        deadline = findViewById(R.id.txtviewDeadline);
        contactName = findViewById(R.id.txtviewContactName);
        contactEmail = findViewById(R.id.txtviewContactEmail);
        contactPhone = findViewById(R.id.txtviewContactPhone);
        minQ = findViewById(R.id.txtviewMinQ);

        title.setText(jobPost.getTitle().toString());
        location.setText(jobPost.getLocation().toString());
        description.setText(jobPost.getDescription().toString());
        salary.setText(jobPost.getSalary().toString());
        deadline.setText(jobPost.getDeadline().toString());
        contactName.setText(jobPost.getContactName().toString());
        contactEmail.setText(jobPost.getContactEmail().toString());
        contactPhone.setText(jobPost.getContactPhone().toString());
        minQ.setText(jobPost.getMinimumQ().toString());

    }

    public void sendEmail(View view)
    {
        String recipientList = contactEmail.getText().toString();
        String[] recipients = recipientList.split(",");

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}