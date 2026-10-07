package com.facilityflow.workorder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateWorkOrderRequest {

    @NotBlank
    @Size(max = 150)
    private String title;

    private String description;

    @NotNull
    private WorkOrderPriority priority;

    @Size(max = 150)
    private String location;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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

    public void setPriority(WorkOrderPriority priority) {
        this.priority = priority;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}