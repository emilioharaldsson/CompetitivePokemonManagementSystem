package com.emilio.expenseapp.dao;


import com.emilio.expenseapp.model.Move;
import com.emilio.expenseapp.model.Pokemon;
import com.emilio.expenseapp.model.PokemonMove;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonMoveDAO extends JpaRepository<PokemonMove, Long> {
    boolean existsByPokemonAndMove(Pokemon pokemon, Move move);
}
