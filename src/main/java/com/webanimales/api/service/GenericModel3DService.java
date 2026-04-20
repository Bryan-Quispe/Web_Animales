package com.webanimales.api.service;

import com.webanimales.api.entity.GenericModel3D;
import com.webanimales.api.repository.GenericModel3DRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericModel3DService {

    private final GenericModel3DRepository genericModel3DRepository;

    public GenericModel3DService(GenericModel3DRepository genericModel3DRepository) {
        this.genericModel3DRepository = genericModel3DRepository;
    }

    public List<GenericModel3D> findAll() {
        return genericModel3DRepository.findAll();
    }

    public GenericModel3D findById(Long id) {
        return genericModel3DRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("GenericModel3D not found: " + id));
    }

    public GenericModel3D create(GenericModel3D genericModel3D) {
        return genericModel3DRepository.save(genericModel3D);
    }

    public GenericModel3D update(Long id, GenericModel3D updatedModel) {
        GenericModel3D existing = findById(id);
        existing.setSpecies(updatedModel.getSpecies());
        existing.setBreedName(updatedModel.getBreedName());
        existing.setGlbFileUrl(updatedModel.getGlbFileUrl());
        return genericModel3DRepository.save(existing);
    }

    public void delete(Long id) {
        GenericModel3D existing = findById(id);
        genericModel3DRepository.delete(existing);
    }
}
