package com.emilio.expenseapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="team_pokemon")
@Getter
@Setter
public class TeamPokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @ManyToOne
    private Team team;
    @ManyToOne
    private Pokemon pokemon;
    @ManyToOne
    private Move move1;
    @ManyToOne
    private Move move2;
    @ManyToOne
    private Move move3;
    @ManyToOne
    private Move move4;
}
