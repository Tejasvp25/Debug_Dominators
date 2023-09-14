package com.debug.dominators.model;

public class ProjectMember {
    private int id;
    private int projectId;
    private int userId;
    private int typeOfUserId;
    
    public ProjectMember() {
    	
    }

    public ProjectMember(int id, int projectId, int userId, int typeOfUserId) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.userId = userId;
		this.typeOfUserId = typeOfUserId;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTypeOfUserId() {
        return typeOfUserId;
    }

    public void setTypeOfUserId(int typeOfUserId) {
        this.typeOfUserId = typeOfUserId;
    }
}
