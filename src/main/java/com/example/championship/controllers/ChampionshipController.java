package com.example.championship.controllers;

import com.example.championship.model.Championship;
import com.example.championship.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasPermission('championship','readById')")
    public Championship getChampionshipById(@PathVariable String id) {
        return championshipService.findById(id);
    }

    @PostMapping("/addChampionship")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasPermission('championship','created')")
    public Championship addChampionship(@RequestBody Championship championship){
        return championshipService.createChampionship(championship);
    }

    @PutMapping("/updateChampionship")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasPermission('championship','update')")
    public Championship updateChampionship(@RequestBody Championship championship) {
        return championshipService.updateChampionship(championship);
    }

    @GetMapping("/all")
    @PreAuthorize("hasPermission('championship','read')")
    public List<Championship> allChampionship() {
        return championshipService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission('championship','deleted')")
    public void deleteChampionship(@PathVariable String id) {
       championshipService.delete(id);
    }

}
