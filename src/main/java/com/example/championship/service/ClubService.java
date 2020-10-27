package com.example.championship.service;

import com.example.championship.exceptions.EntityAlreadyExistsException;
import com.example.championship.exceptions.EntityHasDetailsException;
import com.example.championship.exceptions.EntityIllegalArgumentException;
import com.example.championship.exceptions.EntityNotFoundException;
import com.example.championship.model.Championship;
import com.example.championship.model.Club;

import java.util.List;

public interface ClubService {

    List<Club> findAll();
    /**
     * Поиск клуба по id
     * @param id
     * @return Club
     */
    Club findById(Object id);

    /**
     * Поиск клуба по названию
     * @param name
     * @return Club
     */
    Club findByName(String name);

    /**
     * Поиск клубов по id чемпионата
     * @param id
     * @return List Club
     */
    List<Club> findByClubByChampioshipId(Object id);

    /**
     * Создание клуба
     * @param club объект
     * @return Club
     */
    Club createClub(Club club);

    /**
     * Удаление клуба
     * @param id идентификатор
     */
    void delete(Object id);

    /**
     * Обновление клуба
     * @param club объект
     * @return Club
     */
    Club updateClub(Club club);
}
