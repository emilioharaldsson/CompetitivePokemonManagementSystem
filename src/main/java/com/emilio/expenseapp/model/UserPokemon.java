package com.emilio.expenseapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user_pokemon")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name="pokemon_id", nullable = false)
    private Pokemon pokemon;
    @Column(name="quantity", nullable=false)
    private int quantity;

}
