package com.emilio.expenseapp.controller;

import com.emilio.expenseapp.dao.MoveDAO;
import com.emilio.expenseapp.dao.PokemonDAO;
import com.emilio.expenseapp.dao.TeamDAO;
import com.emilio.expenseapp.dao.UserDAO;
import com.emilio.expenseapp.dto.TeamDTO;
import com.emilio.expenseapp.dto.TeamPokemonDTO;
import com.emilio.expenseapp.model.Pokemon;
import com.emilio.expenseapp.model.Team;
import com.emilio.expenseapp.model.TeamPokemon;
import com.emilio.expenseapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/team")
public class TeamController {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PokemonDAO pokemonDAO;
    @Autowired
    private MoveDAO moveDAO;
    @Autowired
    private TeamDAO teamDAO;
    @PostMapping("/add/{userId}")
    public ResponseEntity<?> registerNewUserTeam(@PathVariable Integer userId, @RequestBody TeamDTO teamRequestBody){
        User user = userDAO.findUserById(userId);
        Team newTeam = new Team();
        newTeam.setName(teamRequestBody.getName());
        newTeam.setUser(user);

        for (TeamPokemonDTO teamPokemonDTO : teamRequestBody.getTeamPokemons()){
            Pokemon pokemon = pokemonDAO.findPokemonById(teamPokemonDTO.getPokemonId());
            TeamPokemon teamPokemon = new TeamPokemon();
            teamPokemon.setTeam(newTeam);
            teamPokemon.setPokemon(pokemon);
            teamPokemon.setMove1(moveDAO.findById(teamPokemonDTO.getMove1Id()));
            teamPokemon.setMove2(moveDAO.findById(teamPokemonDTO.getMove2Id()));
            teamPokemon.setMove3(moveDAO.findById(teamPokemonDTO.getMove3Id()));
            teamPokemon.setMove4(moveDAO.findById(teamPokemonDTO.getMove4Id()));
            newTeam.getTeamPokemons().add(teamPokemon);
        }
        teamDAO.save(newTeam);
        return ResponseEntity.ok().body("Success");
    }
}
