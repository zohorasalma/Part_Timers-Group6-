package com.example.parttimers.utility;

import com.example.parttimers.model.JobPost;
import com.example.parttimers.model.User;

import javax.inject.Singleton;

public class ApplicationData
{
    @Singleton
    public static JobPost jobPostDetails = new JobPost();
    @Singleton
    public static User userDetails = new User();

    public  static String user_email ;

}
