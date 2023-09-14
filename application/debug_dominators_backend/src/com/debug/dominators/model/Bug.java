package com.debug.dominators.model;

import java.util.Date;

public class Bug {
    private int id;
    private String title;
    private String description;
    private int projectId;
    private int createdById;
    private Date openDate;
    private int assignedToId;
    private boolean markedForClosing; // Changed to boolean
    private int closedById;
    private Date closedOn;
    private int status; // Assuming status is a string
    private int severityLevelId;

    public Bug(int id, String title, String description, int projectId, int createdById, Date openDate,
			int assignedToId, boolean markedForClosing, int closedById, Date closedOn, int status,
			int severityLevelId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.projectId = projectId;
		this.createdById = createdById;
		this.openDate = openDate;
		this.assignedToId = assignedToId;
		this.markedForClosing = markedForClosing;
		this.closedById = closedById;
		this.closedOn = closedOn;
		this.status = status;
		this.severityLevelId = severityLevelId;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public int getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(int assignedToId) {
        this.assignedToId = assignedToId;
    }

    public boolean getMarkedForClosing() {
        return markedForClosing;
    }

    public void setMarkedForClosing(boolean markedForClosing) {
        this.markedForClosing = markedForClosing;
    }

    public int getClosedById() {
        return closedById;
    }

    public void setClosedById(int closedById) {
        this.closedById = closedById;
    }

    public Date getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(Date closedOn) {
        this.closedOn = closedOn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSeverityLevelId() {
        return severityLevelId;
    }

    public void setSeverityLevelId(int severityLevelId) {
        this.severityLevelId = severityLevelId;
    }
}
