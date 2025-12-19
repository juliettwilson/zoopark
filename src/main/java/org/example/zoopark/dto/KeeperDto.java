package org.example.zoopark.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class KeeperDto {
    private Long id;
    private String name;
    private int experience;
    private Set<AnimalDto> animalIds;

}
