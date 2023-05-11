package com.emilio.expenseapp.service;

import com.emilio.expenseapp.dao.PokemonDAO;
import com.emilio.expenseapp.dao.UserDAO;
import com.emilio.expenseapp.model.Pokemon;
import com.emilio.expenseapp.model.User;
import com.emilio.expenseapp.model.UserPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PokemonDAO pokemonDAO;

    public User saveUser(User user){
        return userDAO.save(user);
    }

    public User addPokemonToUser(Integer userId, Integer pokemonId){
        User user = userDAO.findUserById(userId);
        Pokemon pokemon = pokemonDAO.findPokemonById(pokemonId);
        UserPokemon userPokemon = user.getUserPokemons().stream()
                .filter(up -> up.getPokemon().getId().equals(pokemonId))
                .findFirst()
                .orElse(null);

        if (userPokemon == null){
            userPokemon = new UserPokemon();
            userPokemon.setUser(user);
            userPokemon.setPokemon(pokemon);
            userPokemon.setQuantity(1);
            user.getUserPokemons().add(userPokemon);
        } else {
            userPokemon.setQuantity(userPokemon.getQuantity());
        }

        return userDAO.save(user);
    }

    public ArrayList<String> getUserPokemons(Integer userId){
        User user = userDAO.findUserById(userId);
        ArrayList<String> pokemonNames = user.getUserPokemons().stream()
                .map(userPokemon -> userPokemon.getPokemon().getName())
                .collect(Collectors.toCollection(ArrayList:: new));
        return pokemonNames;
    }

}
