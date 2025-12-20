package org.example.zoopark.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;
    private int age;

    @ManyToMany
    private List<Keeper> keepers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "enclosure_id")
    private Enclosure enclosure;

}
