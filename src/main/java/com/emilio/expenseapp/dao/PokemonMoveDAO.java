package com.emilio.expenseapp.dao;


import com.emilio.expenseapp.model.PokemonMove;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonMoveDAO extends JpaRepository<PokemonMove, Long> {

}
