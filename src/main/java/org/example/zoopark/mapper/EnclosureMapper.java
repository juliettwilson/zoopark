package org.example.zoopark.mapper;

import org.example.zoopark.dto.EnclosureDto;
import org.example.zoopark.entity.Enclosure;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnclosureMapper {

    EnclosureDto toDto(Enclosure enclosure);

    Enclosure toEntity(EnclosureDto dto);

    List<EnclosureDto> toDtoList(List<Enclosure> enclosures);

    List<Enclosure> toEntityList(List<EnclosureDto> dtos);
}
