package com.example.championship.controller;

import com.example.championship.controllers.ChampionshipController;
import com.example.championship.model.Championship;
import com.example.championship.service.mock.MockChampionshipService;
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
@SpringBootTest(classes = {ChampionshipController.class, MockChampionshipService.class})
public class ChampionshipControllerTest {

    private final ChampionshipController championshipController;

    @Autowired
    public ChampionshipControllerTest(ChampionshipController championshipController) {
        this.championshipController = championshipController;
    }

    private MockMvc mockMvc;
    private final static String URL = "http://localhost:8080/api/v1/championship";
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(championshipController).build();
    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(get(URL + "/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void createTest() throws Exception {
        Championship championship = new Championship(null, "Test", "Test desc", 15);
        String requestJson = mapper.writeValueAsString(championship);
        mockMvc.perform(post(URL + "/addChampionship").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateTest() throws Exception {
        Championship championship = new Championship(1L, "Test", "Test desc", 15);
        String requestJson = mapper.writeValueAsString(championship);
        mockMvc.perform(put(URL + "/updateChampionship").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdTest() throws Exception {
        mockMvc.perform(get(URL + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete(URL + "/delete/1"))
                .andExpect(status().isNoContent());
    }
}
