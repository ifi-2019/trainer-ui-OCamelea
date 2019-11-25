package com.ifi.trainer_ui.trainers.service;

import com.ifi.trainer_ui.pokemonTypes.bo.PokemonType;
import com.ifi.trainer_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService  {

    protected String pokemonServiceUrl;
    protected RestTemplate restTemplate;

    public List<Trainer> getAllTrainers() {
        var url = pokemonServiceUrl + "/trainers/";
        var trainers = restTemplate.getForObject(url, Trainer[].class);
        return Arrays.asList(trainers);
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}
