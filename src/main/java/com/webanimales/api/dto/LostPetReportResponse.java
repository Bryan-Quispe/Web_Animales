package com.webanimales.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LostPetReportResponse {

    private Long id;
    @JsonProperty("pet_name")
    private String petName;
    @JsonProperty("specific_description")
    private String specificDescription;
    private String status;
    private Double latitude;
    private Double longitude;
    @JsonProperty("generic_model_id")
    private Long genericModelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getSpecificDescription() {
        return specificDescription;
    }

    public void setSpecificDescription(String specificDescription) {
        this.specificDescription = specificDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getGenericModelId() {
        return genericModelId;
    }

    public void setGenericModelId(Long genericModelId) {
        this.genericModelId = genericModelId;
    }
}
