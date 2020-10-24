package com.example.championship;

import com.example.championship.jpaRepository.ChampionshipRepository;
import com.example.championship.model.Championship;
import com.example.championship.model.Club;
import org.checkerframework.checker.units.qual.C;
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
public class ChampionshipRepositoryTest {
    @Autowired
    private ChampionshipRepository championshipRepository;

    @BeforeEach
    public void createChampionship(){
        Championship championship = new Championship();
        championship.setDescription("Test desc");
        championship.setMaxCountClub(15);
        championship.setName("Чемпионат Турции");
        Championship championship1 = championshipRepository.save(championship);
        assertNotNull(championship1);
    }

    @Test
    public void findById(){
        Championship championship = championshipRepository.findById(2L).orElse(null);
        assertNotNull(championship);
        assertEquals(championship.getName(),"Бундес лига");
    }
    @Test
    public void findByName(){
        Championship championship = championshipRepository.findByName("РФПЛ");
        assertNotNull(championship);
        assertEquals(championship.getName(),"РФПЛ");
    }
    @Test
    public void findAll(){
        List<Championship> championshipList = championshipRepository.findAll();
        assertEquals(championshipList.size(),6);
    }
    @AfterEach
    public void deleteChampionship(){
        Championship championship = championshipRepository.findByName("Чемпионат Турции");
        assertNotNull(championship);
        assertEquals(championship.getName(),"Чемпионат Турции");
        championshipRepository.deleteById(championship.getId());
        List<Championship> championshipList = championshipRepository.findAll();
        assertEquals(championshipList.size(),5);
    }

}

