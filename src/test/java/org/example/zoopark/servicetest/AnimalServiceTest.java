package org.example.zoopark.servicetest;

import org.example.zoopark.dto.AnimalDto;
import org.example.zoopark.service.AnimalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.BooleanSupplier;

@SpringBootTest
class AnimalServiceTest {

    @Autowired
    private AnimalService animalService;

    private AnimalDto createTestAnimal() {
        AnimalDto dto = new AnimalDto();
        dto.setName("Test Animal");
        dto.setSpecies("Test Species");
        dto.setAge(3);
        return animalService.create(dto);
    }

    @Test
    void getAllTest() {
        AnimalDto created = createTestAnimal();

        List<AnimalDto> animals = animalService.getAll();

        Assertions.assertNotNull(animals);
        Assertions.assertFalse(animals.isEmpty());

        AnimalDto found = animals.stream()
                .filter(a -> a.getId().equals(created.getId()))
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(found);
        Assertions.assertNotNull(found.getName());
        Assertions.assertNotNull(found.getSpecies());
    }

    @Test
    void getByIdTest() {
        AnimalDto created = createTestAnimal();

        AnimalDto animal = animalService.getById(created.getId());

        Assertions.assertNotNull(animal);
        Assertions.assertEquals(created.getId(), animal.getId());
        Assertions.assertEquals(created.getName(), animal.getName());
        Assertions.assertEquals(created.getSpecies(), animal.getSpecies());
        Assertions.assertEquals(created.getAge(), animal.getAge());
    }

    @Test
    void addTest() {
        AnimalDto dto = new AnimalDto();
        dto.setName("Lion");
        dto.setSpecies("Panthera leo");
        dto.setAge(5);

        AnimalDto saved = animalService.create(dto);

        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("Lion", saved.getName());
        Assertions.assertEquals("Panthera leo", saved.getSpecies());
        Assertions.assertEquals(5, saved.getAge());
    }

    @Test
    void updateTest() {
        AnimalDto created = createTestAnimal();

        AnimalDto updateDto = new AnimalDto();
        updateDto.setName("Updated Name");
        updateDto.setSpecies("Updated Species");
        updateDto.setAge(10);

        AnimalDto updated = animalService.update(created.getId(), updateDto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(created.getId(), updated.getId());
        Assertions.assertEquals("Updated Name", updated.getName());
        Assertions.assertEquals("Updated Species", updated.getSpecies());
        Assertions.assertEquals(10, updated.getAge());
    }

    @Test
    void deleteTest() {
        AnimalDto created = createTestAnimal();

        BooleanSupplier deleted = animalService.delete(created.getId());
        Assertions.assertTrue(deleted);

        AnimalDto afterDelete = animalService.getById(created.getId());
        Assertions.assertNull(afterDelete);
    }
}





