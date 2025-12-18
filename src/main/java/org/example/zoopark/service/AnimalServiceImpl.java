package org.example.zoopark.service;

import lombok.RequiredArgsConstructor;
import org.example.zoopark.entity.Animal;
import org.example.zoopark.repository.AnimalRepository;
import org.example.zoopark.service.AnimalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    @Override
    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public Animal findById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found with id " + id));
    }

    @Override
    public void deleteById(Long id) {
        animalRepository.deleteById(id);
    }
}
