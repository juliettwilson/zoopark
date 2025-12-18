package org.example.zoopark.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "animal_name")
    private String name;
    @Column(name = "animal_species")
    private String species;
    @Column(name = "animal_age")
    private int age;

    @ManyToMany
    private Set<Keeper> keepers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "enclosure_id")
    private Enclosure enclosure;

}
