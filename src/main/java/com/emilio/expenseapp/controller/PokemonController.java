package com.emilio.expenseapp.controller;

import com.emilio.expenseapp.dao.PokemonDAO;
import com.emilio.expenseapp.model.User;
import com.emilio.expenseapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
    @Autowired
    private UserService userService;

    @PostMapping("/add/{userId}/{pokemonId}")
    public ResponseEntity<User>  registerPokemonToUser(@PathVariable int userId, @PathVariable int pokemonId){
        User updateUser = userService.addPokemonToUser(userId, pokemonId);
        return ResponseEntity.ok(updateUser);
    }

    @GetMapping("/count/{userId}")
    public ResponseEntity<Map<String, ArrayList<String>>>getUserPokemon(@PathVariable Integer userId){
        Map<String, ArrayList<String>> responseObject = new HashMap<>();
        responseObject.put("pokemon", userService.getUserPokemons(userId));
        return ResponseEntity.ok(responseObject);
    }

}
