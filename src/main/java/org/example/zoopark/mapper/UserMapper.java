package org.example.zoopark.mapper;

import org.example.zoopark.dto.UserDto;
import org.example.zoopark.entity.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDto toDto(UserModel user);


    UserModel toEntity(UserDto dto);


    List<UserDto> toDtoList(List<UserModel> user);
}
