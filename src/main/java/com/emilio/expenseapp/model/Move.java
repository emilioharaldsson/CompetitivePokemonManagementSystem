package com.emilio.expenseapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="move")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Move {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="category")
    private String category;

    @Column(name="PP")
    private String pp;

    @Column(name="power")
    private String power;

    @Column(name ="accuracy")
    private String accuracy;

    @Column(name="gen")
    private String gen;

    @OneToMany(mappedBy = "move", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<PokemonMove> pokemonMove = new HashSet<>();

}
