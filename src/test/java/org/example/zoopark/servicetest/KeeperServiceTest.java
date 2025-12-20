package org.example.zoopark.servicetest;

import org.example.zoopark.dto.KeeperDto;
import org.example.zoopark.service.KeeperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.function.BooleanSupplier;

@SpringBootTest
class KeeperServiceTest {

    @Autowired
    private KeeperService keeperService;

    private KeeperDto createTestKeeper() {
        KeeperDto dto = new KeeperDto();
        dto.setName("Test Keeper");
        dto.setExperience(5);

        KeeperDto saved = keeperService.create(dto);

        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());

        return saved;
    }

    @Test
    void getAllTest() {
        createTestKeeper();

        List<KeeperDto> keepers = keeperService.getAll();

        Assertions.assertNotNull(keepers);
        Assertions.assertFalse(keepers.isEmpty());

        for (KeeperDto dto : keepers) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
        }
    }

    @Test
    void getByIdTest() {
        KeeperDto created = createTestKeeper();

        KeeperDto keeper = keeperService.getById(created.getId());

        Assertions.assertNotNull(keeper);
        Assertions.assertEquals(created.getId(), keeper.getId());
        Assertions.assertEquals(created.getName(), keeper.getName());
    }

    @Test
    void addTest() {
        KeeperDto dto = new KeeperDto();
        dto.setName("Lion Keeper");
        dto.setExperience(7);

        KeeperDto saved = keeperService.create(dto);

        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals(dto.getName(), saved.getName());
        Assertions.assertEquals(dto.getExperience(), saved.getExperience());
    }

    @Test
    void updateTest() {
        KeeperDto created = createTestKeeper();

        KeeperDto updateDto = new KeeperDto();
        updateDto.setName("Updated Keeper");
        updateDto.setExperience(10);

        KeeperDto updated = keeperService.update(created.getId(), updateDto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(created.getId(), updated.getId());
        Assertions.assertEquals("Updated Keeper", updated.getName());
        Assertions.assertEquals(10, updated.getExperience());
    }

    @Test
    void deleteTest() {
        KeeperDto created = createTestKeeper();

        BooleanSupplier deleted = keeperService.delete(created.getId());
        Assertions.assertTrue(deleted);

        KeeperDto afterDelete = keeperService.getById(created.getId());
        Assertions.assertNull(afterDelete);
    }
}






