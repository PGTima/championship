package com.example.championship.jdbcRepository;


import com.example.championship.model.Championship;
import com.example.championship.model.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChampionshipJdbcRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ChampionshipJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from public.championship", Integer.class);
    }

    public List<Championship> allChampionship(){
        return jdbcTemplate.query("select * from public.championship",((resultSet, i) ->
              new Championship(resultSet.getLong("id"),
                               resultSet.getString("name"),
                               resultSet.getString("description"),
                               resultSet.getInt("maxcountclub")
                )));
    }

    public List<Championship> getChampionshipCountClub(int countClub){
        return jdbcTemplate.query(String.format("select * from public.championship t where t.maxcountclub=%s", countClub),
                ((resultSet, i) ->
                new Championship(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("maxcountclub")
                )));
    }

    public List<Club> getClubByChampionship(int id){
        return jdbcTemplate.query(String.format("select t.* from " +
                                                       "public.championship c," +
                                                       "public.club  t " +
                                                "where   t.championship_id = c.id " +
                                                    "and t.championship_id = %s", id),
                ((resultSet, i) ->
                        new Club(resultSet.getLong("id"),
                                 resultSet.getString("name"),
                                 resultSet.getString("description")
                        )));
    }
}
