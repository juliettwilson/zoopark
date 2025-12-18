package org.example.zoopark.service;

import org.example.zoopark.entity.Animal;

import java.util.List;

public interface AnimalService {
    Animal save(Animal animal);

    List<Animal> findAll();

    Animal findById(Long id);
    void deleteById(Long id);
}