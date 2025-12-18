package org.example.zoopark.mapperTest;

import org.example.zoopark.dto.AnimalDto;
import org.example.zoopark.entity.Animal;
import org.example.zoopark.mapper.AnimalMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AnimalMapperTest {

    @Autowired
    private AnimalMapper animalMapper;

    @Test
    void convertEntityToDtoTest() {
        Animal animalEntity = new Animal();
        animalEntity.setId(1L);
        animalEntity.setName("Lion");
        animalEntity.setSpecies("Panthera leo");
        animalEntity.setAge(5);

        AnimalDto animalDto = animalMapper.toDto(animalEntity);

        Assertions.assertNotNull(animalDto);
        Assertions.assertNotNull(animalDto.getId());
        Assertions.assertNotNull(animalDto.getName());
        Assertions.assertNotNull(animalDto.getSpecies());
        Assertions.assertNotNull(animalDto.getAge());

        Assertions.assertEquals(animalEntity.getId(), animalDto.getId());
        Assertions.assertEquals(animalEntity.getName(), animalDto.getName());
        Assertions.assertEquals(animalEntity.getSpecies(), animalDto.getSpecies());
        Assertions.assertEquals(animalEntity.getAge(), animalDto.getAge());
    }

    @Test
    void convertDtoToEntityTest() {
        AnimalDto animalDto = new AnimalDto();
        animalDto.setId(1L);
        animalDto.setName("Lion");
        animalDto.setSpecies("Panthera leo");
        animalDto.setAge(5);

        Animal animalEntity = animalMapper.toEntity(animalDto);

        Assertions.assertNotNull(animalEntity);
        Assertions.assertNotNull(animalEntity.getId());
        Assertions.assertNotNull(animalEntity.getName());
        Assertions.assertNotNull(animalEntity.getSpecies());
        Assertions.assertNotNull(animalEntity.getAge());

        Assertions.assertEquals(animalDto.getId(), animalEntity.getId());
        Assertions.assertEquals(animalDto.getName(), animalEntity.getName());
        Assertions.assertEquals(animalDto.getSpecies(), animalEntity.getSpecies());
        Assertions.assertEquals(animalDto.getAge(), animalEntity.getAge());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Animal> animalEntityList = new ArrayList<>();
        animalEntityList.add(new Animal(){ { setId(1L); setName("Lion"); setSpecies("Panthera leo"); setAge(5); } });
        animalEntityList.add(new Animal(){ { setId(2L); setName("Tiger"); setSpecies("Panthera tigris"); setAge(4); } });
        animalEntityList.add(new Animal(){ { setId(3L); setName("Leopard"); setSpecies("Panthera pardus"); setAge(3); } });

        List<AnimalDto> animalDtoList = animalMapper.toDtoList(animalEntityList);

        Assertions.assertNotNull(animalDtoList);
        Assertions.assertNotEquals(0, animalDtoList.size());
        Assertions.assertEquals(animalEntityList.size(), animalDtoList.size());

        for (int i = 0; i < animalEntityList.size(); i++) {
            Animal animalEntity = animalEntityList.get(i);
            AnimalDto animalDto = animalDtoList.get(i);

            Assertions.assertNotNull(animalDto);
            Assertions.assertNotNull(animalDto.getId());
            Assertions.assertNotNull(animalDto.getName());
            Assertions.assertNotNull(animalDto.getSpecies());
            Assertions.assertNotNull(animalDto.getAge());

            Assertions.assertEquals(animalEntity.getId(), animalDto.getId());
            Assertions.assertEquals(animalEntity.getName(), animalDto.getName());
            Assertions.assertEquals(animalEntity.getSpecies(), animalDto.getSpecies());
            Assertions.assertEquals(animalEntity.getAge(), animalDto.getAge());
        }
    }
}
