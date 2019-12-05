package com.ifi.trainer_ui.pokemonTypes.service;

import com.ifi.trainer_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    protected String pokemonServiceUrl;
    protected RestTemplate restTemplate;

    public List<PokemonType> listPokemonsTypes() {
        var url = pokemonServiceUrl + "/pokemon-types/";
        var pokemons = restTemplate.getForObject(url, PokemonType[].class);
        return Arrays.asList(pokemons);
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }

    @Retryable
    public PokemonType getPokemonType(int id) {
        var pokemon = restTemplate.getForObject(pokemonServiceUrl+"/pokemon-types/{id}",PokemonType.class,id);
        return pokemon;
    }
}