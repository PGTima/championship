package com.example.championship.service;

import com.example.championship.model.Championship;

import java.util.List;

public interface ChampionshipService {

    List<Championship> findAll();

    /**
     *  Поиск чемпионата по id
     * @param id идентификатор чемпионата
     * @return
     */
    Championship findById(Object id);

    /**
     * Создание Чемпионата
     * @param championship объект
     * @return Championship
     */
    Championship createChampionship(Championship championship);

    /**
     * Удаление чемпионата
     * @param id идентификатор
     */
    void delete(Object id);

    /**
     * Обновление чемпионата
     * @param championship объект
     * @return Championship
     */
    Championship updateChampionship(Championship championship);
}
