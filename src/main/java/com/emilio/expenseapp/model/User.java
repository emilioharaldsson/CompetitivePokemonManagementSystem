package com.emilio.expenseapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="full_name")
    private String full_name;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<UserPokemon> userPokemons = new HashSet<>();

}
