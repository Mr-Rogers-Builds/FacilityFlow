package com.facilityflow.site.location;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateLocationTypeRequest {

    @NotNull
    @Size(max = 100)
    private String locationTypeName;

    public String getLocationTypeName() {
        return locationTypeName;
    }

    public void setLocationTypeName(String locationTypeName) {
        this.locationTypeName = locationTypeName;
    }

}
