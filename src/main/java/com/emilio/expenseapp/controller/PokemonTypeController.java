package com.emilio.expenseapp.controller;


import com.emilio.expenseapp.model.Pokemon;
import com.emilio.expenseapp.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pokemon/type")
public class PokemonTypeController {
    @Autowired
    private PokemonService pokemonService;

    @PostMapping("/{pokemonId}/{typeId}")
    public ResponseEntity<Pokemon> registerPokemonType (@PathVariable Integer pokemonId, @PathVariable Integer typeId){
        Pokemon updatedPokemon = pokemonService.registerPokemonType(pokemonId, typeId);
        return ResponseEntity.ok(updatedPokemon);
    }
}
