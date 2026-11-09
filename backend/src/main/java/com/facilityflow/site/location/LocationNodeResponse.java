package com.facilityflow.site.location;

public class LocationNodeResponse {

    private Long id;

    private Long locationTypeId;

    private Long parentId;

    private String name;

    public Long getId() {
        return id;
    }

    public Long getLocationTypeId() {
        return locationTypeId;
    }

    public Long getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocationTypeId(Long locationTypeId) {
        this.locationTypeId = locationTypeId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
