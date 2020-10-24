package com.example.championship.controllers;

import com.example.championship.jdbcRepository.ChampionshipJdbcRepository;
import com.example.championship.jpaRepository.ChampionshipRepository;
import com.example.championship.jpaRepository.ClubRepository;
import com.example.championship.model.Championship;
import com.example.championship.model.Club;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/champinship")
public class ChampinshipController {

    private final ChampionshipJdbcRepository championshipJdbcRepository;
    private final ChampionshipRepository championshipRepository;

    public ChampinshipController(ChampionshipJdbcRepository championshipJdbcRepository, ChampionshipRepository championshipRepository) {
        this.championshipJdbcRepository = championshipJdbcRepository;
        this.championshipRepository = championshipRepository;
    }

    //Jdbc
    @GetMapping("count")
    public Integer getCountChampionship() {
        return championshipJdbcRepository.count();
    }

    @GetMapping("/allChampionship")
    public List<Championship> allChampionship() {
        return championshipJdbcRepository.allChampionship();
    }

    @GetMapping("/getClubByChampionship/{id}")
    public List<Club> allChampionship(@PathVariable int id) {
        return championshipJdbcRepository.getClubByChampionship(id);
    }

    @GetMapping("/getChampionshipCountClub/{count}")
    public List<Championship> getChampionshipCountClub(@PathVariable int count) {
        return championshipJdbcRepository.getChampionshipCountClub(count);
    }

    //JPA

    @GetMapping("/championshipById/{id}")
    public Championship getChampionshipById(@PathVariable Long id) {
        return championshipRepository.findById(id).orElse(null);
    }

    @PostMapping("/addChampionship")
    public Championship addChampionship(@RequestBody Championship championship){
        return championshipRepository.save(championship);
    }

    @GetMapping("/championshipByName/{name}")
    public Championship getChampionshipById(@PathVariable String name) {
        return championshipRepository.findByName(name);
    }

}
