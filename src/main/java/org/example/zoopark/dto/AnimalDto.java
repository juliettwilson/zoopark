package org.example.zoopark.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AnimalDto {
    private Long id;
    private String name;
    private String species;
    private int age;
    private Set<Long> keeperIds;

}
