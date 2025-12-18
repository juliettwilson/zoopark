package org.example.zoopark.service;

import lombok.RequiredArgsConstructor;
import org.example.zoopark.dto.AnimalDto;
import org.example.zoopark.entity.Animal;
import org.example.zoopark.mapper.AnimalMapper;
import org.example.zoopark.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    @Override
    public AnimalDto create(AnimalDto animalDto) {
        Animal animal = animalMapper.toEntity(animalDto);
        Animal saved = animalRepository.save(animal);
        return animalMapper.toDto(saved);
    }

    @Override
    public List<AnimalDto> getAll() {
        List<Animal> animals = animalRepository.findAll();
        return animalMapper.toDtoList(animals);
    }

    @Override
    public AnimalDto getById(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found with id " + id));
        return animalMapper.toDto(animal);
    }

    @Override
    public AnimalDto update(Long id, AnimalDto animalDto) {
        Animal existing = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found with id " + id));

        existing.setName(animalDto.getName());
        existing.setSpecies(animalDto.getSpecies());
        existing.setAge(animalDto.getAge());

        Animal updated = animalRepository.save(existing);
        return animalMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        Animal existing = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found with id " + id));
        animalRepository.delete(existing);
    }
}
