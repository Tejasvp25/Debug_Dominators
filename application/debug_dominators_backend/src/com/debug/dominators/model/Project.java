package com.debug.dominators.model;

import java.sql.Date;

public class Project {
    private int projectId;
    private String projectName;
    private String description;
    private Date startDate;
    private Date endDate;
    private int status;
    private int projectManagerId;

//    private List<ProjectMember> projectMembers;

    public Project(int projectManagerId) {
    	this.projectManagerId = projectManagerId;
    }
    
    public int getProjectId() {
        return projectId;
    }

    public Project(int projectId, String projectName, String description, Date startDate, Date endDate, int status,
		int projectManagerId) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.projectManagerId = projectManagerId;
    }

	public Project() {
		// TODO Auto-generated constructor stub
	}

	public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(int projectManagerId) {
        this.projectManagerId = projectManagerId;
    }
}