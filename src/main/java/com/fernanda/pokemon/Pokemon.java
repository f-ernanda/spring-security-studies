package com.fernanda.pokemon;

import org.hibernate.annotations.Table;

import javax.persistence.*;

@Entity
@Table(appliesTo = "pokemon")
public class Pokemon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pokemonId;
    private final String pokemonName;

    public Pokemon(Integer pokemonId, String pokemonName) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
    }

    public Pokemon(String pokemonName) {
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
