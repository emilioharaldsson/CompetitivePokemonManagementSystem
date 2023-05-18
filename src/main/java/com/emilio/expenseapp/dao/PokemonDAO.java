package com.emilio.expenseapp.dao;

import com.emilio.expenseapp.dto.PokemonTypeDTO;
import com.emilio.expenseapp.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PokemonDAO extends JpaRepository<Pokemon, Long> {
    @Query("SELECT p FROM Pokemon p")
    public List<Pokemon> getAllPokemon();

    @Query("SELECT p FROM Pokemon p where p.id = :id")
    public Pokemon findPokemonById(Integer id);

    @Query("SELECT p FROM Pokemon p where p.name = :name")
    public Pokemon findPokemonByName(String name);

    @Query(value = "SELECT p.id, p.name, p.atk, p.def, p.spatk, p.spdef, p.spd, p.hp, GROUP_CONCAT(t.name) as types " +
            "FROM pokemon p " +
            "JOIN pokemon_type pt ON p.id = pt.pokemon_id " +
            "JOIN type t ON pt.type_id = t.id " +
            "GROUP BY p.id", nativeQuery = true)
    List<Object[]> findAllWithTypesRaw();


    @Query(value="SELECT p.id, p.name, p.atk, p.def, p.spatk, p.spdef, p.spd, p.hp, GROUP_CONCAT(t.name) as types " +
            "FROM pokemon p " +
            "JOIN pokemon_type pt ON p.id = pt.pokemon_id " +
            "JOIN type t ON pt.type_id = t.id " +
            "WHERE p.id = :id", nativeQuery = true)
    List<Object[]> findPokemonWithTypeRaw(Integer id);

    @Query(value="", nativeQuery = true)
    List<Object[]> findPokemonWithMoveAndTypeRaw(Integer id);
}
