package com.facilityflow.organization;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateOrganizationRequest {

    @NotBlank
    @Size(max = 150)
    private String name;

    @NotBlank
    @Size(max = 150)
    private String hqLocationAddress;
    @NotBlank
    @Size(max = 100)
    private String hqLocationCity;

    @NotBlank
    @Size(max = 100)
    private String hqLocationState;

    @NotBlank
    @Size(max = 20)
    private String hqLocationZip;

    @NotBlank
    @Size(max = 100)
    private String hqLocationCountry;

    @Size(max = 200)
    private String website;

    @NotBlank
    @Size(max = 20)
    private String phoneNumber;

    @NotBlank
    @Size(max = 100)
    private String email;

    @Size(max = 20)
    private String faxNumber;

    // Getters and Setters

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
}
