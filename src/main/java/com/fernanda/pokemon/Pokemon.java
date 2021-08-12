package com.fernanda.pokemon;

public class Pokemon {

    private final Integer pokemonId;
    private final String pokemonName;

    public Pokemon(Integer pokemonId, String pokemonName) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
    }

    public Integer getPokemonId() {
        return pokemonId;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonId=" + pokemonId +
                ", pokemonName='" + pokemonName + '\'' +
                '}';
    }
}
