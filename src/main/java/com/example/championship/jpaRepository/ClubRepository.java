package com.example.championship.jpaRepository;

import com.example.championship.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByChampionshipId(Long id);
    Club findByName(String name);
}
