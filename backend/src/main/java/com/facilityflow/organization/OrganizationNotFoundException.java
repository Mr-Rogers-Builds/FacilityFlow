package com.facilityflow.organization;

public class OrganizationNotFoundException extends RuntimeException {

    public OrganizationNotFoundException(Long id) {
        super("Organization not found with id: " + id);
    }
}
