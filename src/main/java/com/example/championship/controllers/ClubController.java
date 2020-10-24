package com.example.championship.controllers;

import com.example.championship.jpaRepository.ClubRepository;
import com.example.championship.model.Championship;
import com.example.championship.model.Club;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/club")
public class ClubController {

    private final ClubRepository clubRepository;

    public ClubController(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @GetMapping("/allClub")
    public List<Club> allClub() {
        return clubRepository.findAll();
    }

    @GetMapping("/clubById/{id}")
    public Club getClubById(@PathVariable Long id) {
        return clubRepository.findById(id).orElse(null);
    }

    @PostMapping("/addClub")
    public Club addClub(@RequestBody Club club){
        return clubRepository.save(club);
    }

    @GetMapping("/ClubByChampions/{id}")
    public List<Club> getChampionshipById(@PathVariable Long id) {
        return clubRepository.findByChampionshipId(id);
    }
}
