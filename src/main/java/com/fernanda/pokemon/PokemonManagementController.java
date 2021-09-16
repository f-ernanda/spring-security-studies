package com.fernanda.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("admin/pokemon")
public class PokemonManagementController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')") //aceita: hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')
    public List<Pokemon> getAllPokemon() {
        return pokemonService.getAllPokemon();
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

