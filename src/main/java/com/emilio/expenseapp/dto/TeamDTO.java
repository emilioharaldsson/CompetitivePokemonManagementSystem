package com.emilio.expenseapp.dto;

import com.emilio.expenseapp.model.TeamPokemon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {
    private String name;
    private List<TeamPokemonDTO> teamPokemons;
}
