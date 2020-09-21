package com.example.parttimers.utility;

import com.example.parttimers.model.JobPost;

import javax.inject.Singleton;

public class ApplicationData
{
    @Singleton
    public static JobPost jobPostDetails = new JobPost();

}
