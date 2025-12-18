package org.example.zoopark.mapper;

import org.example.zoopark.dto.AnimalDto;
import org.example.zoopark.entity.Animal;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    AnimalDto toDto(Animal animal);

    Animal toEntity(AnimalDto dto);

    List<AnimalDto> toDtoList(List<Animal> animals);

    List<Animal> toEntityList(List<AnimalDto> dtos);
}
