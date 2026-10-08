package com.facilityflow.workorder;

import jakarta.validation.constraints.Size;

public class UpdateWorkOrderRequest {

    @Size(max = 150)
    private String title;

    private String description;

    private WorkOrderStatus status;

    private WorkOrderPriority priority;

    @Size(max = 150)
    private String location;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public WorkOrderStatus getStatus() {
        return status;
    }

    public WorkOrderPriority getPriority() {
        return priority;
    }

    public String getLocation() {
        return location;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(WorkOrderStatus status) {
        this.status = status;
    }

    public void setPriority(WorkOrderPriority priority) {
        this.priority = priority;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
