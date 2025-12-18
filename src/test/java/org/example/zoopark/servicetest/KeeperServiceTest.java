package org.example.zoopark.servicetest;

import org.example.zoopark.dto.KeeperDto;
import org.example.zoopark.service.KeeperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class KeeperServiceTest {

    @Autowired
    private KeeperService keeperService;

    @Test
    void getAllTest() {
        List<KeeperDto> keeperDtos = keeperService.getAll();

        Assertions.assertNotNull(keeperDtos);
        Assertions.assertNotEquals(0, keeperDtos.size());

        for (KeeperDto dto : keeperDtos) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
            Assertions.assertNotNull(dto.getExperience());
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        List<KeeperDto> keeperDtos = keeperService.getAll();
        int randomIndex = random.nextInt(keeperDtos.size());
        Long someIndex = keeperDtos.get(randomIndex).getId();

        KeeperDto keeperDto = keeperService.getById(someIndex);

        Assertions.assertNotNull(keeperDto);
        Assertions.assertNotNull(keeperDto.getId());
        Assertions.assertNotNull(keeperDto.getName());
        Assertions.assertNotNull(keeperDto.getExperience());

        Assertions.assertEquals(someIndex, keeperDto.getId());
    }

    @Test
    void addTest() {
        KeeperDto keeperDto = new KeeperDto();
        keeperDto.setName("John");
        keeperDto.setExperience(5);

        KeeperDto add = keeperService.create(keeperDto);

        Assertions.assertNotNull(add);
        Assertions.assertNotNull(add.getId());
        Assertions.assertNotNull(add.getName());
        Assertions.assertNotNull(add.getExperience());

        KeeperDto added = keeperService.getById(add.getId());

        Assertions.assertNotNull(added);
        Assertions.assertNotNull(added.getId());
        Assertions.assertNotNull(added.getName());
        Assertions.assertNotNull(added.getExperience());

        Assertions.assertEquals(add.getId(), added.getId());
        Assertions.assertEquals(add.getName(), added.getName());
        Assertions.assertEquals(add.getExperience(), added.getExperience());
    }

    @Test
    void updateTest() {
        Random random = new Random();
        List<KeeperDto> keeperDtos = keeperService.getAll();
        int randomIndex = random.nextInt(keeperDtos.size());
        Long someIndex = keeperDtos.get(randomIndex).getId();

        KeeperDto newKeeper = new KeeperDto();
        newKeeper.setId(someIndex);
        newKeeper.setName("Updated John");
        newKeeper.setExperience(10);

        KeeperDto update = keeperService.update(someIndex, newKeeper);

        Assertions.assertNotNull(update);
        Assertions.assertNotNull(update.getId());
        Assertions.assertNotNull(update.getName());
        Assertions.assertNotNull(update.getExperience());

        Assertions.assertEquals(newKeeper.getId(), update.getId());
        Assertions.assertEquals(newKeeper.getName(), update.getName());
        Assertions.assertEquals(newKeeper.getExperience(), update.getExperience());

        KeeperDto updated = keeperService.getById(someIndex);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getName());
        Assertions.assertNotNull(updated.getExperience());

        Assertions.assertEquals(update.getId(), updated.getId());
        Assertions.assertEquals(update.getName(), updated.getName());
        Assertions.assertEquals(update.getExperience(), updated.getExperience());
    }

    @Test
    void deleteTest() {
        Random random = new Random();
        List<KeeperDto> keeperDtos = keeperService.getAll();
        int randomIndex = random.nextInt(keeperDtos.size());
        Long someIndex = keeperDtos.get(randomIndex).getId();
        Assertions.assertTrue(keeperService.delete(someIndex));

        KeeperDto deleted = keeperService.getById(someIndex);
        Assertions.assertNull(deleted);
    }
}