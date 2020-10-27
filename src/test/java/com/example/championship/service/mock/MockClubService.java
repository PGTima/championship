package com.example.championship.service.mock;

import com.example.championship.model.Club;
import com.example.championship.service.ClubService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockClubService implements ClubService {
    @Override
    public List<Club> findAll() {
        return null;
    }

    @Override
    public Club findById(Object id) {
        return new Club(Long.valueOf(id.toString()),"Test","ttt");
    }

    @Override
    public Club findByName(String name) {
        return new Club(1L,name,"ttt");
    }

    @Override
    public List<Club> findByClubByChampioshipId(Object id) {
        return new ArrayList<>();
    }

    @Override
    public Club createClub(Club club) {
        return club;
    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public Club updateClub(Club club) {
        return club;
    }
}
