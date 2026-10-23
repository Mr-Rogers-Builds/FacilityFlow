package com.facilityflow.site.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateLocationNodeRequest {

    @NotNull
    private Long locationTypeId;

    @NotBlank
    @Size(max = 200)
    private String locationNodeName;

    private Long parentId;

    public Long getLocationTypeId() {
        return locationTypeId;
    }

    public void setLocationTypeId(Long locationTypeId) {
        this.locationTypeId = locationTypeId;
    }

    public String getLocationNodeName() {
        return locationNodeName;
    }

    public void setLocationNodeName(String locationNodeName) {
        this.locationNodeName = locationNodeName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
