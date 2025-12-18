package org.example.zoopark.mapperTest;

import org.example.zoopark.dto.EnclosureDto;
import org.example.zoopark.entity.Animal;
import org.example.zoopark.entity.Enclosure;
import org.example.zoopark.mapper.EnclosureMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EnclosureMapperTest {

    @Autowired
    private EnclosureMapper enclosureMapper;

    @Test
    void convertEntityToDtoTest() {
        Enclosure enclosureEntity = new Enclosure();
        enclosureEntity.setId(1L);
        enclosureEntity.setName("Savannah");
        enclosureEntity.setType("Outdoor");
        enclosureEntity.setAnimals(List.of(
                new Animal(){ { setId(1L); } },
                new Animal(){ { setId(2L); } }
        ));

        EnclosureDto enclosureDto = enclosureMapper.toDto(enclosureEntity);

        Assertions.assertNotNull(enclosureDto);
        Assertions.assertNotNull(enclosureDto.getId());
        Assertions.assertNotNull(enclosureDto.getName());
        Assertions.assertNotNull(enclosureDto.getType());
        Assertions.assertNotNull(enclosureDto.getAnimalIds());

        Assertions.assertEquals(enclosureEntity.getId(), enclosureDto.getId());
        Assertions.assertEquals(enclosureEntity.getName(), enclosureDto.getName());
        Assertions.assertEquals(enclosureEntity.getType(), enclosureDto.getType());
        Assertions.assertEquals(2, enclosureDto.getAnimalIds().size());
        Assertions.assertEquals(1L, enclosureDto.getAnimalIds().get(0));
        Assertions.assertEquals(2L, enclosureDto.getAnimalIds().get(1));
    }

    @Test
    void convertDtoToEntityTest() {
        EnclosureDto enclosureDto = new EnclosureDto();
        enclosureDto.setId(1L);
        enclosureDto.setName("Savannah");
        enclosureDto.setType("Outdoor");
        enclosureDto.setAnimalIds(List.of(1L, 2L));

        Enclosure enclosureEntity = enclosureMapper.toEntity(enclosureDto);

        Assertions.assertNotNull(enclosureEntity);
        Assertions.assertNotNull(enclosureEntity.getId());
        Assertions.assertNotNull(enclosureEntity.getName());
        Assertions.assertNotNull(enclosureEntity.getType());
        Assertions.assertNotNull(enclosureEntity.getAnimals());

        Assertions.assertEquals(enclosureDto.getId(), enclosureEntity.getId());
        Assertions.assertEquals(enclosureDto.getName(), enclosureEntity.getName());
        Assertions.assertEquals(enclosureDto.getType(), enclosureEntity.getType());
        Assertions.assertEquals(2, enclosureEntity.getAnimals().size());
        Assertions.assertEquals(1L, enclosureEntity.getAnimals().get(0).getId());
        Assertions.assertEquals(2L, enclosureEntity.getAnimals().get(1).getId());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Enclosure> enclosureEntityList = new ArrayList<>();

        Enclosure e1 = new Enclosure();
        e1.setId(1L); e1.setName("Savannah"); e1.setType("Outdoor");
        e1.setAnimals(List.of(new Animal(){ { setId(1L); } }));

        Enclosure e2 = new Enclosure();
        e2.setId(2L); e2.setName("Jungle"); e2.setType("Indoor");
        e2.setAnimals(List.of(new Animal(){ { setId(2L); } }));

        enclosureEntityList.add(e1);
        enclosureEntityList.add(e2);

        List<EnclosureDto> enclosureDtoList = enclosureMapper.toDtoList(enclosureEntityList);

        Assertions.assertNotNull(enclosureDtoList);
        Assertions.assertEquals(enclosureEntityList.size(), enclosureDtoList.size());
        Assertions.assertEquals(1L, enclosureDtoList.get(0).getAnimalIds().get(0));
        Assertions.assertEquals(2L, enclosureDtoList.get(1).getAnimalIds().get(0));
    }
}

