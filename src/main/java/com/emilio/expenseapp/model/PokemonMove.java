package com.emilio.expenseapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pokemon_move")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonMove {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="pokemon_id", nullable=false)
    private Pokemon pokemon;
    @ManyToOne
    @JoinColumn(name="move_id", nullable=false)
    private Move move;
}
