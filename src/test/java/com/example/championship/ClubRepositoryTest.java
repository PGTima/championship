package com.example.championship;

import com.example.championship.jpaRepository.ChampionshipRepository;
import com.example.championship.jpaRepository.ClubRepository;
import com.example.championship.model.Club;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class ClubRepositoryTest {

    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private ChampionshipRepository championshipRepository;

    @BeforeEach
    public void createClub(){
        Club club = new Club();
        club.setName("Локомотив");
        club.setDescription("Клуб из Москвы");
        club.setChampionship(championshipRepository.findById(1L).orElse(null));
        Club club1 = clubRepository.save(club);
        assertNotNull(club1);
    }

    @Test
    public void findAllTest(){
        List<Club> clubList = clubRepository.findAll();
        assertEquals(clubList.size(),6);
    }

    @Test
    public void findById(){
        Club club = clubRepository.findById(1L).orElse(null);
        assertNotNull(club);
    }

    @Test
    public void findByName(){
        Club club = clubRepository.findByName("Спартак");
        assertNotNull(club);
    }

    @Test
    public void findByIdCheckName(){
        Club club = clubRepository.findById(1L).orElse(null);
        assertEquals(club.getName(), "Спартак");
    }

    @Test
    public void findByChampionshipId(){
        List<Club> clubList = clubRepository.findByChampionshipId(1L);
        assertEquals(clubList.size(),3);
    }

    @AfterEach
    public void deleteById(){
        Club club = clubRepository.findByName("Локомотив");
        clubRepository.deleteById(club.getId());
        List<Club> clubList = clubRepository.findAll();
        assertEquals(clubList.size(),5);
    }

}
