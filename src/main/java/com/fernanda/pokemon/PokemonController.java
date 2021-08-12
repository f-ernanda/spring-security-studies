package com.fernanda.pokemon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
//a annotation @RestController retorna os dados do objetos como uma resposta HTTP, como JSON ou XML - é a combinação do @Controller com o @ResponseBody
@RequestMapping("pokemon/myteam")
public class PokemonController {

    private static final List<Pokemon> POKEMON_LIST = Arrays.asList(
            new Pokemon(1, "Bulbasaur"),
            new Pokemon(2, "Ivysaur"),
            new Pokemon (3, "Venusaur"));

    @GetMapping
    public List<Pokemon> getMyPokemon() {
        return POKEMON_LIST;

    }

}
