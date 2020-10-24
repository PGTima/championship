package com.example.championship.jpaRepository;

import com.example.championship.model.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionshipRepository extends JpaRepository<Championship, Long> {
    Championship findByName(String name);
}
