package com.webanimales.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lost_pet_report")
public class LostPetReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pet_name", nullable = false)
    private String petName;

    @Column(name = "specific_description", nullable = false)
    private String specificDescription;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @ManyToOne(optional = false)
    @JoinColumn(name = "generic_model_id", nullable = false)
    private GenericModel3D genericModel;

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

    public GenericModel3D getGenericModel() {
        return genericModel;
    }

    public void setGenericModel(GenericModel3D genericModel) {
        this.genericModel = genericModel;
    }
}
