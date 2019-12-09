package com.ifi.trainer_ui.trainers.service;

import com.ifi.trainer_ui.pokemonTypes.bo.PokemonType;
import com.ifi.trainer_ui.pokemonTypes.service.PokemonTypeService;
import com.ifi.trainer_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import com.ifi.trainer_ui.pokemonTypes.bo.PokemonType;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Cacheable;
import java.util.Arrays;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService  {

    protected String pokemonServiceUrl;
    protected RestTemplate restTemplate;
    private PokemonTypeService pokemonTypeService;

    @Override
    @Retryable
    public List<Trainer> getAllTrainers() {
        var url = pokemonServiceUrl + "/trainers/";
        var trainers = restTemplate.getForObject(url, Trainer[].class);
        var trainersList = Arrays.asList(trainers);
        trainersList.parallelStream().forEach(trainer -> {
            trainer.getTeam().parallelStream().forEach(pokemon -> {
                pokemon.setType(pokemonTypeService.getPokemonType(pokemon.getPokemonType()));
            });
        });
        return trainersList;
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Value("${trainer.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }

    public PokemonTypeService getPokemonTypeService() {
        return pokemonTypeService;
    }


    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }
}
