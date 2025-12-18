package org.example.zoopark.servicetest;

import org.example.zoopark.dto.EnclosureDto;
import org.example.zoopark.service.EnclosureService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class EnclosureServiceTest {

    @Autowired
    private EnclosureService enclosureService;

    @Test
    void getAllTest() {
        List<EnclosureDto> enclosureDtos = enclosureService.getAll();

        Assertions.assertNotNull(enclosureDtos);
        Assertions.assertNotEquals(0, enclosureDtos.size());

        for (EnclosureDto dto : enclosureDtos) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
            Assertions.assertNotNull(dto.getType());
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        List<EnclosureDto> enclosureDtos = enclosureService.getAll();
        int randomIndex = random.nextInt(enclosureDtos.size());
        Long someIndex = enclosureDtos.get(randomIndex).getId();

        EnclosureDto enclosureDto = enclosureService.getById(someIndex);

        Assertions.assertNotNull(enclosureDto);
        Assertions.assertNotNull(enclosureDto.getId());
        Assertions.assertNotNull(enclosureDto.getName());
        Assertions.assertNotNull(enclosureDto.getType());

        Assertions.assertEquals(someIndex, enclosureDto.getId());
    }

    @Test
    void addTest() {
        EnclosureDto enclosureDto = new EnclosureDto();
        enclosureDto.setName("Savannah");
        enclosureDto.setType("Outdoor");

        EnclosureDto add = enclosureService.create(enclosureDto);

        Assertions.assertNotNull(add);
        Assertions.assertNotNull(add.getId());
        Assertions.assertNotNull(add.getName());
        Assertions.assertNotNull(add.getType());

        EnclosureDto added = enclosureService.getById(add.getId());

        Assertions.assertNotNull(added);
        Assertions.assertNotNull(added.getId());
        Assertions.assertNotNull(added.getName());
        Assertions.assertNotNull(added.getType());

        Assertions.assertEquals(add.getId(), added.getId());
        Assertions.assertEquals(add.getName(), added.getName());
        Assertions.assertEquals(add.getType(), added.getType());
    }

    @Test
    void updateTest() {
        Random random = new Random();
        List<EnclosureDto> enclosureDtos = enclosureService.getAll();
        int randomIndex = random.nextInt(enclosureDtos.size());
        Long someIndex = enclosureDtos.get(randomIndex).getId();

        EnclosureDto newEnclosure = new EnclosureDto();
        newEnclosure.setId(someIndex);
        newEnclosure.setName("Updated Name");
        newEnclosure.setType("Updated Type");

        EnclosureDto update = enclosureService.update(someIndex, newEnclosure);

        Assertions.assertNotNull(update);
        Assertions.assertNotNull(update.getId());
        Assertions.assertNotNull(update.getName());
        Assertions.assertNotNull(update.getType());

        Assertions.assertEquals(newEnclosure.getId(), update.getId());
        Assertions.assertEquals(newEnclosure.getName(), update.getName());
        Assertions.assertEquals(newEnclosure.getType(), update.getType());

        EnclosureDto updated = enclosureService.getById(someIndex);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getName());
        Assertions.assertNotNull(updated.getType());

        Assertions.assertEquals(update.getId(), updated.getId());
        Assertions.assertEquals(update.getName(), updated.getName());
        Assertions.assertEquals(update.getType(), updated.getType());
    }
    @Test
    void deleteTest() {
        Random random = new Random();
        List<EnclosureDto> enclosureDtos = enclosureService.getAll();
        int randomIndex = random.nextInt(enclosureDtos.size());
        Long someIndex = enclosureDtos.get(randomIndex).getId();

        Assertions.assertTrue(enclosureService.delete(someIndex));

        EnclosureDto deleted = enclosureService.getById(someIndex);
        Assertions.assertNull(deleted);
    }
}