package com.emilio.expenseapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="pokemon")
public class Pokemon {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="atk")
    private int attack;
    @Column(name="def")
    private int defense;
    @Column(name="spatk")
    private int spattack;
    @Column(name="spdef")
    private int spdefense;
    @Column(name="spd")
    private int speed;
    @Column(name="hp")
    private int hp;

    @OneToMany(mappedBy="pokemon", cascade= CascadeType.ALL, orphanRemoval=true)
    @JsonIgnore
    private Set<UserPokemon> userPokemons = new HashSet<>();

    @OneToMany(mappedBy="pokemon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<PokemonType> pokemonTypes = new HashSet<>();

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<PokemonMove> pokemonMoves = new HashSet<>();
}
