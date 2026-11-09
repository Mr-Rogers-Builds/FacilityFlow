package com.facilityflow.organization;

public class OrganizationResponse {

    private Long id;

    private String name;

    private String hqLocationAddress;

    private String hqLocationCity;

    private String hqLocationState;

    private String hqLocationZip;

    private String hqLocationCountry;

    private boolean active = true;

    private String website;

    private String phoneNumber;

    private String email;

    private String faxNumber;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHqLocationAddress() {
        return hqLocationAddress;
    }

    public void setHqLocationAddress(String hqLocationAddress) {
        this.hqLocationAddress = hqLocationAddress;
    }

    public String getHqLocationCity() {
        return hqLocationCity;
    }

    public void setHqLocationCity(String hqLocationCity) {
        this.hqLocationCity = hqLocationCity;
    }

    public String getHqLocationState() {
        return hqLocationState;
    }

    public void setHqLocationState(String hqLocationState) {
        this.hqLocationState = hqLocationState;
    }

    public String getHqLocationZip() {
        return hqLocationZip;
    }

    public void setHqLocationZip(String hqLocationZip) {
        this.hqLocationZip = hqLocationZip;
    }

    public String getHqLocationCountry() {
        return hqLocationCountry;
    }

    public void setHqLocationCountry(String hqLocationCountry) {
        this.hqLocationCountry = hqLocationCountry;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    // Getters and Setters end

}
