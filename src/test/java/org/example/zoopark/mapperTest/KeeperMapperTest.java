package org.example.zoopark.mapperTest;

import org.example.zoopark.dto.KeeperDto;
import org.example.zoopark.entity.Keeper;
import org.example.zoopark.mapper.KeeperMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class KeeperMapperTest {

    @Autowired
    private KeeperMapper keeperMapper;

    @Test
    void convertEntityToDtoTest() {
        Keeper keeperEntity = new Keeper();
        keeperEntity.setId(1L);
        keeperEntity.setName("John");
        keeperEntity.setExperience(10);

        KeeperDto keeperDto = keeperMapper.toDto(keeperEntity);

        Assertions.assertNotNull(keeperDto);
        Assertions.assertNotNull(keeperDto.getId());
        Assertions.assertNotNull(keeperDto.getName());
        Assertions.assertNotNull(keeperDto.getExperience());

        Assertions.assertEquals(keeperEntity.getId(), keeperDto.getId());
        Assertions.assertEquals(keeperEntity.getName(), keeperDto.getName());
        Assertions.assertEquals(keeperEntity.getExperience(), keeperDto.getExperience());
    }

    @Test
    void convertDtoToEntityTest() {
        KeeperDto keeperDto = new KeeperDto();
        keeperDto.setId(1L);
        keeperDto.setName("John");
        keeperDto.setExperience(10);

        Keeper keeperEntity = keeperMapper.toEntity(keeperDto);

        Assertions.assertNotNull(keeperEntity);
        Assertions.assertNotNull(keeperEntity.getId());
        Assertions.assertNotNull(keeperEntity.getName());
        Assertions.assertNotNull(keeperEntity.getExperience());

        Assertions.assertEquals(keeperDto.getId(), keeperEntity.getId());
        Assertions.assertEquals(keeperDto.getName(), keeperEntity.getName());
        Assertions.assertEquals(keeperDto.getExperience(), keeperEntity.getExperience());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Keeper> keeperEntityList = new ArrayList<>();
        keeperEntityList.add(new Keeper(){ { setId(1L); setName("John"); setExperience(10); } });
        keeperEntityList.add(new Keeper(){ { setId(2L); setName("Jane"); setExperience(5); } });
        keeperEntityList.add(new Keeper(){ { setId(3L); setName("Mike"); setExperience(7); } });

        List<KeeperDto> keeperDtoList = keeperMapper.toDtoList(keeperEntityList);

        Assertions.assertNotNull(keeperDtoList);
        Assertions.assertNotEquals(0, keeperDtoList.size());
        Assertions.assertEquals(keeperEntityList.size(), keeperDtoList.size());

        for (int i = 0; i < keeperEntityList.size(); i++) {
            Keeper keeperEntity = keeperEntityList.get(i);
            KeeperDto keeperDto = keeperDtoList.get(i);

            Assertions.assertNotNull(keeperDto);
            Assertions.assertNotNull(keeperDto.getId());
            Assertions.assertNotNull(keeperDto.getName());
            Assertions.assertNotNull(keeperDto.getExperience());

            Assertions.assertEquals(keeperEntity.getId(), keeperDto.getId());
            Assertions.assertEquals(keeperEntity.getName(), keeperDto.getName());
            Assertions.assertEquals(keeperEntity.getExperience(), keeperDto.getExperience());
        }
    }
}
