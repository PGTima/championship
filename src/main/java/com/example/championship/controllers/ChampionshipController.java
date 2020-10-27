package com.example.championship.controllers;

import com.example.championship.model.Championship;
import com.example.championship.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/championship")
public class ChampionshipController {

    private final ChampionshipService championshipService;

    @Autowired
    public ChampionshipController(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }

    @GetMapping("/{id}")
    public Championship getChampionshipById(@PathVariable String id) {
        return championshipService.findById(id);
    }

    @PostMapping("/addChampionship")
    @ResponseStatus(HttpStatus.CREATED)
    public Championship addChampionship(@RequestBody Championship championship){
        return championshipService.createChampionship(championship);
    }

    @PutMapping("/updateChampionship")
    @ResponseStatus(HttpStatus.OK)
    public Championship updateChampionship(@RequestBody Championship championship) {
        return championshipService.updateChampionship(championship);
    }

    @GetMapping("/all")
    public List<Championship> allChampionship() {
        return championshipService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChampionship(@PathVariable String id) {
       championshipService.delete(id);
    }

}
