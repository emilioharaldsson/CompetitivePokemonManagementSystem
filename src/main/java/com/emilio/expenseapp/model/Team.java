package com.emilio.expenseapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="team")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "team")
    private Set<TeamPokemon> teamPokemons;
    @Column(name="name")
    private String name;
}
