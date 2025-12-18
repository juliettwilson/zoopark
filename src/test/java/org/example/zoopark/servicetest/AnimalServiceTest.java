package org.example.zoopark.servicetest;

import org.example.zoopark.dto.AnimalDto;
import org.example.zoopark.service.AnimalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class AnimalServiceTest {

    @Autowired
    private AnimalService animalService;

    @Test
    void getAllTest() {
        List<AnimalDto> animalDtos = animalService.getAll();

        Assertions.assertNotNull(animalDtos);
        Assertions.assertNotEquals(0, animalDtos.size());

        for (AnimalDto dto : animalDtos) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
            Assertions.assertNotNull(dto.getSpecies());
            Assertions.assertNotNull(dto.getAge());
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        List<AnimalDto> animalDtos = animalService.getAll();
        int randomIndex = random.nextInt(animalDtos.size());
        Long someIndex = animalDtos.get(randomIndex).getId();

        AnimalDto animalDto = animalService.getById(someIndex);

        Assertions.assertNotNull(animalDto);
        Assertions.assertNotNull(animalDto.getId());
        Assertions.assertNotNull(animalDto.getName());
        Assertions.assertNotNull(animalDto.getSpecies());
        Assertions.assertNotNull(animalDto.getAge());

        Assertions.assertEquals(someIndex, animalDto.getId());
    }

    @Test
    void addTest() {
        AnimalDto animalDto = new AnimalDto();
        animalDto.setName("Lion");
        animalDto.setSpecies("Panthera leo");
        animalDto.setAge(5);

        AnimalDto add = animalService.create(animalDto);

        Assertions.assertNotNull(add);
        Assertions.assertNotNull(add.getId());
        Assertions.assertNotNull(add.getName());
        Assertions.assertNotNull(add.getSpecies());
        Assertions.assertNotNull(add.getAge());

        AnimalDto added = animalService.getById(add.getId());

        Assertions.assertNotNull(added);
        Assertions.assertNotNull(added.getId());
        Assertions.assertNotNull(added.getName());
        Assertions.assertNotNull(added.getSpecies());
        Assertions.assertNotNull(added.getAge());

        Assertions.assertEquals(add.getId(), added.getId());
        Assertions.assertEquals(add.getName(), added.getName());
        Assertions.assertEquals(add.getSpecies(), added.getSpecies());
        Assertions.assertEquals(add.getAge(), added.getAge());
    }

    @Test
    void updateTest() {
        Random random = new Random();
        List<AnimalDto> animalDtos = animalService.getAll();
        int randomIndex = random.nextInt(animalDtos.size());
        Long someIndex = animalDtos.get(randomIndex).getId();

        AnimalDto newAnimal = new AnimalDto();
        newAnimal.setId(someIndex);
        newAnimal.setName("Updated Lion");
        newAnimal.setSpecies("Updated Panthera leo");
        newAnimal.setAge(6);

        AnimalDto update = animalService.update(someIndex, newAnimal);

        Assertions.assertNotNull(update);
        Assertions.assertNotNull(update.getId());
        Assertions.assertNotNull(update.getName());
        Assertions.assertNotNull(update.getSpecies());
        Assertions.assertNotNull(update.getAge());

        Assertions.assertEquals(newAnimal.getId(), update.getId());
        Assertions.assertEquals(newAnimal.getName(), update.getName());
        Assertions.assertEquals(newAnimal.getSpecies(), update.getSpecies());
        Assertions.assertEquals(newAnimal.getAge(), update.getAge());

        AnimalDto updated = animalService.getById(someIndex);
        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getName());
        Assertions.assertNotNull(updated.getSpecies());
        Assertions.assertNotNull(updated.getAge());

        Assertions.assertEquals(update.getId(), updated.getId());
        Assertions.assertEquals(update.getName(), updated.getName());
        Assertions.assertEquals(update.getSpecies(), updated.getSpecies());
        Assertions.assertEquals(update.getAge(), updated.getAge());
    }

    @Test
    void deleteTest() {
        Random random = new Random();
        List<AnimalDto> animalDtos = animalService.getAll();
        int randomIndex = random.nextInt(animalDtos.size());
        Long someIndex = animalDtos.get(randomIndex).getId();

        Assertions.assertTrue(animalService.delete(someIndex));

        AnimalDto deleted = animalService.getById(someIndex);
        Assertions.assertNull(deleted);
    }
}
