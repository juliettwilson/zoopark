package org.example.zoopark.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Keeper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "keeper_name")
    private String name;
    @Column(name = "expr_year")
    private int experience;

    @ManyToMany
    private Set<Animal> animals = new HashSet<>();
}
