package org.example.zoopark.mapper;

import org.example.zoopark.dto.KeeperDto;
import org.example.zoopark.entity.Keeper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KeeperMapper {
    KeeperDto toDto(Keeper keeper);

    Keeper toEntity(KeeperDto dto);

    List<KeeperDto> toDtoList(List<Keeper> keepers);

    List<Keeper> toEntityList(List<KeeperDto> dtos);
}
