package com.webanimales.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "generic_model_3d")
public class GenericModel3D {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String species;

    @Column(name = "breed_name", nullable = false)
    @JsonProperty("breed_name")
    private String breedName;

    @Column(name = "glb_file_url", nullable = false)
    @JsonProperty("glb_file_url")
    private String glbFileUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getGlbFileUrl() {
        return glbFileUrl;
    }

    public void setGlbFileUrl(String glbFileUrl) {
        this.glbFileUrl = glbFileUrl;
    }
}
