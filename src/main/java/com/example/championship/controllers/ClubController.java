package com.example.championship.controllers;

import com.example.championship.model.Club;
import com.example.championship.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/club")
public class ClubController {

    private final ClubService clubService;
    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/allClub")
    public List<Club> allClub() {
        return clubService.findAll();
    }

    @GetMapping("/clubById/{id}")
    public Club getClubById(@PathVariable String id) {
        return clubService.findById(id);
    }

    @PostMapping("/addClub")
    @ResponseStatus(HttpStatus.CREATED)
    public Club addClub(@RequestBody Club club){
        return clubService.createClub(club);
    }

    @GetMapping("/clubByChampions/{id}")
    public List<Club> getChampionshipById(@PathVariable String id) {
        return clubService.findByClubByChampioshipId(id);
    }

    @GetMapping("/findByName/{name}")
    public Club findByName(@PathVariable String name) {
        return clubService.findByName(name);
    }

    @PutMapping("/updateClub")
    @ResponseStatus(HttpStatus.OK)
    public Club updateClub(@RequestBody Club club) {
        return clubService.updateClub(club);
    }
}
