package com.example.championship.service.mock;

import com.example.championship.model.Championship;
import com.example.championship.service.ChampionshipService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockChampionshipService implements ChampionshipService {
    @Override
    public List<Championship> findAll() {
        return null;
    }

    @Override
    public Championship findById(Object id) {
        return new Championship(Long.valueOf(id.toString()), "Test","test",10);
    }

    @Override
    public Championship createChampionship(Championship championship) {
        return championship;
    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public Championship updateChampionship(Championship championship) {
        return championship;
    }
}
