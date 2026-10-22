package com.facilityflow.organization;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "hq_location_address", nullable = false, length = 150)
    private String hqLocationAddress;

    @Column(name = "hq_location_city", nullable = false, length = 100)
    private String hqLocationCity;

    @Column(name = "hq_location_state", nullable = false, length = 100)
    private String hqLocationState;

    @Column(name = "hq_location_zip", nullable = false, length = 20)
    private String hqLocationZip;

    @Column(name = "hq_location_country", nullable = false, length = 100)
    private String hqLocationCountry;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "website", length = 200)
    private String website;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "fax_number", length = 20)
    private String faxNumber;

    @Column(name = "added_at", nullable = false, updatable = false)
    private OffsetDateTime addedAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = "archived_at")
    private OffsetDateTime archivedAt;

    @PrePersist
    protected void onCreate() {
        addedAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }

    // Getters and Setters (where appropriate)

    public Long getId() {
        return id;
    }

    public OffsetDateTime getAddedAt() {
        return addedAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
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

    public OffsetDateTime getArchivedAt() {
        return archivedAt;
    }

    public void setArchivedAt(OffsetDateTime archivedAt) {
        this.archivedAt = archivedAt;
    }

}
