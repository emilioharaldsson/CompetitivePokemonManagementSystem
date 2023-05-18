package com.emilio.expenseapp.service;

import com.emilio.expenseapp.dao.MoveDAO;
import com.emilio.expenseapp.dao.PokemonDAO;
import com.emilio.expenseapp.dao.PokemonMoveDAO;
import com.emilio.expenseapp.dao.TypeDAO;
import com.emilio.expenseapp.dto.PokemonMoveDTO;
import com.emilio.expenseapp.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {
    @Autowired
    private PokemonDAO pokemonDAO;
    @Autowired
    private MoveDAO moveDAO;
    @Autowired
    private TypeDAO typeDAO;
    @Autowired
    private PokemonMoveDAO pokemonMoveDAO;
    public Pokemon registerPokemonType(Integer pokemonId, Integer typeId){
        Pokemon pokemon = pokemonDAO.findPokemonById(pokemonId);
        Type type = typeDAO.findTypeById(typeId);
        PokemonType pokemonType = pokemon.getPokemonTypes().stream()
                .filter(pt -> pt.getType().getId().equals(typeId))
                .findFirst()
                .orElse(null);

        if (pokemonType == null){
            pokemonType = new PokemonType();
            pokemonType.setPokemon(pokemon);
            pokemonType.setType(type);
            pokemon.getPokemonTypes().add(pokemonType);
        }
        return pokemonDAO.save(pokemon);
    }

    public void getAllPokemon(){
        List<Pokemon> pokemons = pokemonDAO.getAllPokemon();
        
    }

    @Transactional
    public void registerPokemonMove(List<PokemonMoveDTO> pokemonMoveList){
        for (PokemonMoveDTO pokemonMoveDTO : pokemonMoveList){
            Pokemon pokemon = pokemonDAO.findPokemonByName(pokemonMoveDTO.getName());
            for (String moveName : pokemonMoveDTO.getMoves()){
                Move move = moveDAO.findMoveFromName(moveName);
                if (move == null){
                    System.out.println(pokemon.getName());
                }
                else if (move != null) {
                    PokemonMove pokemonMove = new PokemonMove();
                    pokemonMove.setPokemon(pokemon);
                    pokemonMove.setMove(move);
                    pokemonMoveDAO.save(pokemonMove);

                }
            }
        }
    }
}
