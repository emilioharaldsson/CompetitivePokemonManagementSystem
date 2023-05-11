package com.emilio.expenseapp.controller;

import com.emilio.expenseapp.dao.PokemonDAO;
import com.emilio.expenseapp.dao.UserDAO;
import com.emilio.expenseapp.dto.PokemonDTO;
import com.emilio.expenseapp.dto.PokemonTypeDTO;
import com.emilio.expenseapp.model.Pokemon;
import com.emilio.expenseapp.model.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class IndexController {
    @Autowired
    private PokemonDAO pokemonDAO;

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/api/users/all")
    public List<User> getUsers() {
        return userDAO.getAllUsers();
    }

    @GetMapping("/api/pokemon/{pokemonId}")
    public ResponseEntity<PokemonTypeDTO> getPokemonById(@PathVariable Integer pokemonId){
        List<Object[]> rawPokemonList = pokemonDAO.findPokemonWithTypeRaw(pokemonId);
        if (rawPokemonList.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Object[] rawPokemon = rawPokemonList.get(0);

        System.out.println("Raw Pokemon: " + Arrays.toString(rawPokemon));

        PokemonTypeDTO pokemon = new PokemonTypeDTO(
                ((Number) rawPokemon[0]).intValue(),
                (String) rawPokemon[1],
                ((Number) rawPokemon[2]).intValue(),
                ((Number) rawPokemon[3]).intValue(),
                ((Number) rawPokemon[4]).intValue(),
                ((Number) rawPokemon[5]).intValue(),
                ((Number) rawPokemon[6]).intValue(),
                ((Number) rawPokemon[7]).intValue(),
                Arrays.asList(((String) rawPokemon[8]).split(","))
        );
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/api/pokemon/all")
    public ResponseEntity<List<PokemonTypeDTO>> getAllPokemonWithTypes(){
        List<Object[]> rawPokemons = pokemonDAO.findAllWithTypesRaw();
        List<PokemonTypeDTO> pokemons = new ArrayList<>();

        for (Object[] rawPokemon : rawPokemons){
            Integer id = ((Number) rawPokemon[0]).intValue();
            String name = (String) rawPokemon[1];
            int attack = ((Number) rawPokemon[2]).intValue();
            int defense = ((Number) rawPokemon[3]).intValue();
            int spattack = ((Number) rawPokemon[4]).intValue();
            int spdefense = ((Number) rawPokemon[5]).intValue();
            int speed = ((Number) rawPokemon[6]).intValue();
            int hp = ((Number) rawPokemon[7]).intValue();
            String typesString = (String) rawPokemon[8];
            List<String> types = Arrays.asList(typesString.split(","));
            pokemons.add(new PokemonTypeDTO(id, name, attack, defense, spattack, spdefense, speed, hp, types));
        }
        return ResponseEntity.ok(pokemons);
    }
}
