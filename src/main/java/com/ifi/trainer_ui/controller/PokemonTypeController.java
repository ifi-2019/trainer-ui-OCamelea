package com.ifi.trainer_ui.controller;

import com.ifi.trainer_ui.pokemonTypes.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PokemonTypeController {
     protected PokemonTypeService pokemonTypeService;

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @GetMapping("/pokedex")
    public ModelAndView pokedex(){
        var modelAndView = new ModelAndView("pokedex");
        var pokemonTypes = pokemonTypeService.listPokemonsTypes();
        modelAndView.addObject("pokemonTypes",pokemonTypes);
        return modelAndView;
    }

}