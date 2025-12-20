package org.example.zoopark.servicetest;

import org.example.zoopark.dto.EnclosureDto;
import org.example.zoopark.service.EnclosureService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.function.BooleanSupplier;

@SpringBootTest
public class EnclosureServiceTest {

    @Autowired
    private EnclosureService enclosureService;
    private EnclosureDto createTestEnclosure() {
        EnclosureDto dto = new EnclosureDto();
        dto.setName("Test Enclosure");
        dto.setType("Test Type");
        return enclosureService.create(dto);
    }

    @Test
    void getAllTest() {
        EnclosureDto created = createTestEnclosure();

        List<EnclosureDto> enclosures = enclosureService.getAll();

        Assertions.assertNotNull(enclosures);
        Assertions.assertFalse(enclosures.isEmpty());

        EnclosureDto found = enclosures.stream()
                .filter(e -> e.getId().equals(created.getId()))
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(found);
        Assertions.assertNotNull(found.getName());
        Assertions.assertNotNull(found.getType());
    }



    @Test
    void getByIdTest() {
        EnclosureDto created = createTestEnclosure();

        EnclosureDto enclosure = enclosureService.getById(created.getId());

        Assertions.assertNotNull(enclosure);
        Assertions.assertEquals(created.getId(), enclosure.getId());
        Assertions.assertEquals(created.getName(), enclosure.getName());
        Assertions.assertEquals(created.getType(), enclosure.getType());
    }

    @Test
    void addTest() {
        EnclosureDto dto = new EnclosureDto();
        dto.setName("Savannah");
        dto.setType("Outdoor");

        EnclosureDto saved = enclosureService.create(dto);

        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("Savannah", saved.getName());
        Assertions.assertEquals("Outdoor", saved.getType());
    }

    @Test
    void updateTest() {
        EnclosureDto created = createTestEnclosure();

        EnclosureDto updateDto = new EnclosureDto();
        updateDto.setName("Updated Name");
        updateDto.setType("Updated Type");

        EnclosureDto updated =
                enclosureService.update(created.getId(), updateDto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(created.getId(), updated.getId());
        Assertions.assertEquals("Updated Name", updated.getName());
        Assertions.assertEquals("Updated Type", updated.getType());
    }
    @Test
    void deleteTest() {
        EnclosureDto created = createTestEnclosure();

        BooleanSupplier deleted = enclosureService.delete(created.getId());
        Assertions.assertTrue(deleted.getAsBoolean());

        Assertions.assertThrows(
                RuntimeException.class,
                () -> enclosureService.getById(created.getId())
        );
    }
}