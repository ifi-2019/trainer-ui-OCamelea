package com.ifi.trainer_ui.trainers.bo;
import com.ifi.trainer_ui.pokemonTypes.bo.PokemonType;

import javax.persistence.Embeddable;

@Embeddable
public class Pokemon {

    private int pokemonType;

    private int level;

    private PokemonType type;



    public Pokemon() {
    }

    public Pokemon(int pokemonType, int level) {
        this.pokemonType = pokemonType;
        this.level = level;
    }

    public int getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(int pokemonType) {
        this.pokemonType = pokemonType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }
}