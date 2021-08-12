package com.fernanda.pokemon;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("admin/pokemon")
public class PokemonManagementController {

    private static final List<Pokemon> POKEMON_LIST = Arrays.asList(
            new Pokemon(1, "Bulbasaur"),
            new Pokemon(2, "Ivysaur"),
            new Pokemon(3, "Venusaur"),
            new Pokemon(4, "Charmander"),
            new Pokemon(5, "Charmeleon"),
            new Pokemon(6, "Charizard"),
            new Pokemon(7, "Squirtle"),
            new Pokemon(8, "Wartortle"),
            new Pokemon(9, "Blastoise"));

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')") //aceita: hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')
    public List<Pokemon> getAllPokemon() {
        return POKEMON_LIST;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('pokemon:write')")
    public void registerNewPokemon(@RequestBody Pokemon pokemon) {
        System.out.println("Register");
        System.out.println(pokemon);
    }

    @DeleteMapping(path = "{pokemonId}")
    @PreAuthorize("hasAuthority('pokemon:write')")
    public void deletePokemon(@PathVariable("pokemonId") Integer pokemonId) {
        System.out.println("Delete");
        System.out.println(pokemonId);
    }

    @PutMapping(path = "{pokemonId}")
    @PreAuthorize("hasAuthority('pokemon:write')")
    public void updatePokemon(@PathVariable("pokemonId") Integer pokemonId, @RequestBody Pokemon pokemon) {
        System.out.println("Update");
        System.out.printf("%s %s%n", pokemonId, pokemon);
    }
}

