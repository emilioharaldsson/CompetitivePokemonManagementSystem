package com.emilio.expenseapp.dao;

import com.emilio.expenseapp.model.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoveDAO extends JpaRepository<Move, Long> {
    @Query("SELECT m FROM Move m")
    public List<Move> getAllMoves();

    @Query("SELECT m FROM Move m where m.name =:name")
    public Move findMoveFromName(String name);
}
