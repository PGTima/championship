package com.example.championship.service;

import com.example.championship.config.TestConfig;
import com.example.championship.exceptions.EntityHasDetailsException;
import com.example.championship.exceptions.EntityIllegalArgumentException;
import com.example.championship.exceptions.EntityNotFoundException;
import com.example.championship.model.Championship;
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
public class ChampionshipServiceTest {

    @Autowired
    private ChampionshipService championshipService;

    @Test
    public void findAllTest() {
        List<Championship> championshipList = championshipService.findAll();
        assertEquals(championshipList.size(), 5);
    }

    @Test
    public void createNullChampionshipTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            Championship championship = championshipService.createChampionship(null);
        });
    }

    @Test
    public void createExistIdChampionshipTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            Championship championship = new Championship();
            championship.setId(6L);
            championship.setDescription("Test desc");
            championship.setMaxCountClub(15);
            championship.setName("Чемпионат Турции");
            Championship addChampionship = championshipService.createChampionship(championship);
        });
    }

    @Test
    public void createChampionshipTest() {
        Championship championship = new Championship();
        championship.setDescription("Test desc");
        championship.setMaxCountClub(15);
        championship.setName("Чемпионат Турции");
        Championship addChampionship = championshipService.createChampionship(championship);
        assertNotNull(addChampionship);
    }

    @Test
    public void findByIdNullTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            Championship championship = championshipService.findById(null);
        });
    }

    @Test
    public void findByIdNoCorrectTypeTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            Championship championship = championshipService.findById("id");
        });
    }

    @Test
    public void findByIdNotFoundTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            Championship championship = championshipService.findById(("6"));
        });
    }

    @Test
    public void findByIdTest() {
        Championship championship = championshipService.findById("5");
        assertNotNull(championship);
    }

    @Test
    public void deleteNullIdTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            championshipService.delete(null);
        });
    }

    @Test
    public void deleteNoCorrectTypeIdTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            championshipService.delete("id");
        });
    }

    @Test
    public void deleteNoExistEntityTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            championshipService.delete("6");
        });
    }

    @Test
    public void deleteExistsRelationEntityTest() {
        assertThrows(EntityHasDetailsException.class, () -> {
            championshipService.delete("1");
        });
    }

    @Test
    public void deleteTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            championshipService.delete("3");
            Championship championship = championshipService.findById("3");
        });
    }

    @Test
    public void updateChampionshipNullTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            Championship championship = championshipService.updateChampionship(null);
        });
    }

    @Test
    public void updateChampionshipNotExistsTest() {
        assertThrows(EntityIllegalArgumentException.class, () -> {
            Championship championship = new Championship();
            championship.setDescription("Test desc");
            championship.setMaxCountClub(15);
            championship.setName("Чемпионат Турции");
            Championship updChampionship = championshipService.updateChampionship(championship);
        });
    }

    @Test
    public void updateChampionshipNotExistEntityTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            Championship championship = new Championship();
            championship.setId(6L);
            championship.setDescription("Test desc");
            championship.setMaxCountClub(15);
            championship.setName("Чемпионат Турции");
            Championship updChampionship = championshipService.updateChampionship(championship);
        });
    }

    @Test
    public void updateChampionshipTest() {
        Championship championship = new Championship();
        championship.setId(1L);
        championship.setDescription("Test desc");
        championship.setName("Чемпионат Турции");
        Championship updChampionship = championshipService.updateChampionship(championship);
        assertNotNull(updChampionship);
    }
}
