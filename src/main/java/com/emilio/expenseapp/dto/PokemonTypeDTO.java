package com.emilio.expenseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PokemonTypeDTO {
    private Integer id;
    private String name;
    private int attack;
    private int defense;
    private int spattack;
    private int spdefense;
    private int speed;
    private int hp;
    private List<String> types;
}
