package com.webanimales.api.controller;

import com.webanimales.api.entity.GenericModel3D;
import com.webanimales.api.service.GenericModel3DService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generic-models")
public class GenericModel3DController {

    private final GenericModel3DService genericModel3DService;

    public GenericModel3DController(GenericModel3DService genericModel3DService) {
        this.genericModel3DService = genericModel3DService;
    }

    @GetMapping
    public List<GenericModel3D> getAll() {
        return genericModel3DService.findAll();
    }

    @GetMapping("/{id}")
    public GenericModel3D getById(@PathVariable Long id) {
        return genericModel3DService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericModel3D create(@RequestBody GenericModel3D genericModel3D) {
        return genericModel3DService.create(genericModel3D);
    }

    @PutMapping("/{id}")
    public GenericModel3D update(@PathVariable Long id, @RequestBody GenericModel3D genericModel3D) {
        return genericModel3DService.update(id, genericModel3D);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        genericModel3DService.delete(id);
    }
}
