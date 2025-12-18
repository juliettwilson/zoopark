package org.example.zoopark.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnclosureDto {
    private Long id;
    private String name;
    private String type;
    private List<Long> animalIds;
}
