package com.example.championship.controller;

import com.example.championship.controllers.ClubController;
import com.example.championship.model.Championship;
import com.example.championship.model.Club;
import com.example.championship.service.mock.MockClubService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ClubController.class, MockClubService.class})
public class ClubControllerTest {

    private final ClubController clubController;

    @Autowired
    public ClubControllerTest(ClubController clubController) {
        this.clubController = clubController;
    }

    private MockMvc mockMvc;
    private final static String URL = "http://localhost:8080/api/v1/club";
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(clubController).build();
    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(get(URL + "/allClub"))
                .andExpect(status().isOk());
    }

    @Test
    public void createTest() throws Exception {
        Club club = new Club(null,"Amkar", "Test");
        String requestJson = mapper.writeValueAsString(club);
        mockMvc.perform(post(URL + "/addClub").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateTest() throws Exception {
        Club club = new Club(1L,"Amkar", "Test");
        String requestJson = mapper.writeValueAsString(club);
        mockMvc.perform(put(URL + "/updateClub").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void clubByChampionsTest() throws Exception {
        mockMvc.perform(get(URL + "/clubByChampions/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findByNameTest() throws Exception {
        mockMvc.perform(get(URL + "/findByName/name"))
                .andExpect(status().isOk());
    }

    @Test
    public void clubByIdTest() throws Exception {
        mockMvc.perform(get(URL + "/clubById/1"))
                .andExpect(status().isOk());
    }
}
