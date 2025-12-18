package org.example.zoopark.service;

import org.example.zoopark.dto.AnimalDto;
import org.example.zoopark.entity.Animal;

import java.util.List;

public interface AnimalService {
    AnimalDto create(AnimalDto animalDto);
    List<AnimalDto> getAll();
    AnimalDto getById(Long id);
    AnimalDto update(Long id, AnimalDto animalDto);
    void delete(Long id);
}