package com.ifi.trainer_ui.controller;

import com.ifi.trainer_ui.pokemonTypes.service.PokemonTypeService;
import com.ifi.trainer_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainerController {
    protected TrainerService trainerService;

    @Autowired
    public void setPokemonTypeService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/trainer-list")
    public ModelAndView pokedex(){
        var modelAndView = new ModelAndView("trainers");
        var trainers = trainerService.getAllTrainers();
        modelAndView.addObject("trainerList",trainers);
        return modelAndView;
    }

}