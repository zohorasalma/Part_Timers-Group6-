package com.example.parttimers.model;

public class User
{
    private String id,user_name,user_email,user_pass,education,skill,interest;

    public User()
    {
    }

    public User(
            String id,
            String user_name,
            String user_email,
            String user_pass,
            String education,
            String skill,
            String interest)
    {
        this.id = id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_pass = user_pass;
        this.education = education;
        this.skill = skill;
        this.interest = interest;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUser_name()
    {
        return user_name;
    }

    public void setUser_name(String user_name)
    {
        this.user_name = user_name;
    }

    public String getUser_email()
    {
        return user_email;
    }

    public void setUser_email(String user_email)
    {
        this.user_email = user_email;
    }

    public String getUser_pass()
    {
        return user_pass;
    }

    public void setUser_pass(String user_pass)
    {
        this.user_pass = user_pass;
    }

    public String getEducation()
    {
        return education;
    }

    public void setEducation(String education)
    {
        this.education = education;
    }

    public String getSkill()
    {
        return skill;
    }

    public void setSkill(String skill)
    {
        this.skill = skill;
    }

    public String getInterest()
    {
        return interest;
    }

    public void setInterest(String interest)
    {
        this.interest = interest;
    }
}
