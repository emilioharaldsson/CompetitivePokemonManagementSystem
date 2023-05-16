package com.emilio.expenseapp.controller;

import com.emilio.expenseapp.dto.PokemonMoveDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon/register")
public class PokemonMoveController {

    @PostMapping("/moves")
    public ResponseEntity<Void> registerMoves(@RequestBody List<PokemonMoveDTO> pokemonMoveList){
        return ResponseEntity.ok().build();
    }
}
