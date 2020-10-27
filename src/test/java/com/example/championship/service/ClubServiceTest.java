package com.example.championship.service;

import com.example.championship.config.TestConfig;
import com.example.championship.exceptions.EntityAlreadyExistsException;
import com.example.championship.exceptions.EntityHasDetailsException;
import com.example.championship.exceptions.EntityIllegalArgumentException;
import com.example.championship.exceptions.EntityNotFoundException;
import com.example.championship.model.Club;
import com.example.championship.service.impl.ChampionshipServiceImpl;
import com.example.championship.service.impl.ClubServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class ClubServiceTest {

    private final ClubServiceImpl clubService;
    private final ChampionshipServiceImpl championshipService;

    @Autowired
    public ClubServiceTest(ClubServiceImpl clubService, ChampionshipServiceImpl championshipService) {
        this.clubService = clubService;
        this.championshipService = championshipService;
    }

    @Test
    public void findAllTest() {
        List<Club> clubList = clubService.findAll();
        assertNotNull(clubList);
    }

    @Test
    public void findByIdNullTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            Club club = clubService.findById(null);
        });
    }

    @Test
    public void findByIdNoCorrestTypeTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            Club club = clubService.findById("id");
        });
    }

    @Test
    public void findByIdNotDataFoundTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            Club club = clubService.findById("6");
        });
    }

    @Test
    public void findByIdTest() {
        Club club = clubService.findById("1");
        assertNotNull(club);
    }

    @Test
    public void findByNameNullTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            Club club = clubService.findByName(null);
        });
    }

    @Test
    public void findByNameIsEmptyTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            Club club = clubService.findByName("");
        });
    }

    @Test
    public void findByNoNameEntityTest() {
        Club club = clubService.findByName("Амкар");
        assertNull(club);
    }

    @Test
    public void findByTest() {
        Club club = clubService.findByName("Спартак");
        assertNotNull(club);
    }

    @Test
    public void findByClubByChampioshipIdNullTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            List<Club> clubList = clubService.findByClubByChampioshipId(null);
        });
    }

    @Test
    public void findByClubByChampioshipIdNoCorrectTypeTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            List<Club> clubList = clubService.findByClubByChampioshipId("id");
        });
    }

    @Test
    public void findByClubByChampioshipIdNoExistsTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            List<Club> clubList = clubService.findByClubByChampioshipId("6");
        });
    }

    @Test
    public void findByClubByChampioshipIdTest() {
        List<Club> clubList = clubService.findByClubByChampioshipId("1");
        assertEquals(clubList.size(), 2);
    }

    @Test
    public void createClubNullTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            Club club = clubService.createClub(null);
        });
    }

    @Test
    public void createClubNullChampionshipIdTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            Club club = new Club();
            club.setName("Локомотив");
            club.setDescription("Клуб из Москвы");
            club.setChampionship(championshipService.findById("6"));
            Club addClub = clubService.createClub(club);
        });
    }

    @Test
    public void createClubExistsNameClubTest() {
        assertThrows(EntityAlreadyExistsException.class, () -> {
            Club club = new Club();
            club.setName("Спартак");
            club.setDescription("Клуб из Москвы");
            club.setChampionship(championshipService.findById("1"));
            Club addClub = clubService.createClub(club);
        });
    }

    @Test
    public void createClubGetMaxCountClubTest() {
        assertThrows(EntityHasDetailsException.class, () -> {
            Club club = new Club();
            club.setName("Локомотив");
            club.setDescription("Клуб из Москвы");
            club.setChampionship(championshipService.findById("1"));
            Club addClub = clubService.createClub(club);
        });
    }

    @Test
    public void createClubTest() {
        Club club = new Club();
        club.setName("Интер");
        club.setDescription("Клуб из Италии");
        club.setChampionship(championshipService.findById("5"));
        Club addClub = clubService.createClub(club);
        assertNotNull(addClub);
    }

    @Test
    public void deleteClubTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            clubService.delete("1");
            Club club = clubService.findById("1");
        });
    }

    @Test
    public void updateClubNullTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            Club club = clubService.updateClub(null);
        });
    }

    @Test
    public void updateClubNotExistIdTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            Club club = new Club();
            club.setName("Локомотив");
            club.setDescription("Клуб из Москвы");
            Club updclub = clubService.updateClub(club);
        });
    }

    @Test
    public void updateClubNotExistChampionshipIdTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            Club club = new Club();
            club.setId(1L);
            club.setName("Локомотив");
            club.setDescription("Клуб из Москвы");
            club.setChampionship(championshipService.findById("6"));
            Club updclub = clubService.updateClub(club);
        });
    }

    @Test
    public void updateClubGetMaxCountClubTest() {
        assertThrows(EntityHasDetailsException.class, () -> {
            Club club = new Club();
            club.setId(3L);
            club.setName("Локомотив");
            club.setDescription("Клуб из Москвы");
            club.setChampionship(championshipService.findById("1"));
            Club updclub = clubService.updateClub(club);
        });
    }

    @Test
    public void updateClubTest() {
        Club club = new Club();
        club.setId(1L);
        club.setName("Локомотив");
        club.setDescription("Клуб из Москвы");
        club.setChampionship(championshipService.findById("1"));
        Club updclub = clubService.updateClub(club);
        assertNotNull(updclub);
    }
}
