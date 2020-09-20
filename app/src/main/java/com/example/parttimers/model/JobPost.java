package com.example.parttimers.model;

public class JobPost
{
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    private String id, title,location,description,salary,deadline,companyName,minimumQ;

    public JobPost(
            String id,
            String title,
            String location,
            String description,
            String salary,
            String deadline,
            String companyName,
            String minimumQ)
    {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.salary = salary;
        this.deadline = deadline;
        this.companyName = companyName;
        this.minimumQ = minimumQ;
    }

    public JobPost()
    {
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getSalary()
    {
        return salary;
    }

    public void setSalary(String salary)
    {
        this.salary = salary;
    }

    public String getDeadline()
    {
        return deadline;
    }

    public void setDeadline(String deadline)
    {
        this.deadline = deadline;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getMinimumQ()
    {
        return minimumQ;
    }

    public void setMinimumQ(String minimumQ)
    {
        this.minimumQ = minimumQ;
    }
}
