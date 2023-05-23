package com.emilio.expenseapp.controller;

import com.emilio.expenseapp.dao.*;
import com.emilio.expenseapp.dto.TeamDTO;
import com.emilio.expenseapp.dto.TeamPokemonDTO;
import com.emilio.expenseapp.exception.BadRequestException;
import com.emilio.expenseapp.exception.ResourceNotFoundException;
import com.emilio.expenseapp.model.*;
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
    @Autowired
    private PokemonMoveDAO pokemonMoveDAO;
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
            assignMoveIfPokemonCanLearn(teamPokemonDTO.getMove1Id(), pokemon, teamPokemon, 1);
            assignMoveIfPokemonCanLearn(teamPokemonDTO.getMove2Id(), pokemon, teamPokemon, 2);
            assignMoveIfPokemonCanLearn(teamPokemonDTO.getMove3Id(), pokemon, teamPokemon, 3);
            assignMoveIfPokemonCanLearn(teamPokemonDTO.getMove4Id(), pokemon, teamPokemon, 4);
            newTeam.getTeamPokemons().add(teamPokemon);
        }
        teamDAO.save(newTeam);
        return ResponseEntity.ok().body("Success");
    }

    private void assignMoveIfPokemonCanLearn(Integer moveId, Pokemon pokemon, TeamPokemon teamPokemon, int moveNumber){
        if (moveId != null){
            Move move = moveDAO.findById(moveId);
            if (move == null){
                throw new ResourceNotFoundException("Move", "id", moveId);
            }
            boolean canLearn = pokemonMoveDAO.existsByPokemonAndMove(pokemon, move);
            if (!canLearn){
                throw new BadRequestException("Pokemon with id " + pokemon.getId() + " can't learn move with id " + moveId);
            }
            switch(moveNumber){
                case 1:
                    teamPokemon.setMove1(move);
                    break;
                case 2:
                    teamPokemon.setMove2(move);
                    break;
                case 3:
                    teamPokemon.setMove3(move);
                    break;
                case 4:
                    teamPokemon.setMove4(move);
                    break;
            }
        }
    }
}
