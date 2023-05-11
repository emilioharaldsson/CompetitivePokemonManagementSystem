package com.emilio.expenseapp.service;

import com.emilio.expenseapp.dao.PokemonDAO;
import com.emilio.expenseapp.dao.TypeDAO;
import com.emilio.expenseapp.model.Pokemon;
import com.emilio.expenseapp.model.PokemonType;
import com.emilio.expenseapp.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {
    @Autowired
    private PokemonDAO pokemonDAO;

    @Autowired
    private TypeDAO typeDAO;
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

}
