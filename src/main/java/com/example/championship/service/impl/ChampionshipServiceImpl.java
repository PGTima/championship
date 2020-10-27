package com.example.championship.service.impl;

import com.example.championship.exceptions.EntityHasDetailsException;
import com.example.championship.exceptions.EntityIllegalArgumentException;
import com.example.championship.exceptions.EntityNotFoundException;
import com.example.championship.jpaRepository.ChampionshipRepository;
import com.example.championship.jpaRepository.ClubRepository;
import com.example.championship.model.Championship;
import com.example.championship.model.Club;
import com.example.championship.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionshipServiceImpl implements ChampionshipService {

    private final ClubRepository clubRepository;
    private final ChampionshipRepository championshipRepository;

    @Autowired
    public ChampionshipServiceImpl(ClubRepository clubRepository, ChampionshipRepository championshipRepository) {
        this.clubRepository = clubRepository;
        this.championshipRepository = championshipRepository;
    }
    @Override
    public List<Championship> findAll(){
        return championshipRepository.findAll();
    }

    /**
     *  Поиск чемпионата по id
     * @param id идентификатор чемпионата
     * @return
     */
    @Override
    public Championship findById(Object id){
        Championship championship;
        if(id == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть пустым");
        }
        Long parsedId;
        try{
            parsedId = Long.valueOf((String) id);
        } catch(NumberFormatException ex){
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор к  нужному типу, текст ошибки %s", ex));
        }
        championship = championshipRepository.findById(parsedId).orElse(null);
        if (championship == null){
            throw new EntityNotFoundException(Championship.TYPE_NAME,parsedId);
        }
        return championship;
    }

    /**
     * Создание Чемпионата
     * @param championship объект
     * @return Championship
     */
    @Override
    public Championship createChampionship(Championship championship){

        if (championship == null){
            throw new EntityIllegalArgumentException("Объект не может быть пустым");
        }

        if (championship.getId() != null){
            throw new EntityIllegalArgumentException("Идентификатор объекта для создания должен быть пустым");
        }

        return championshipRepository.save(championship);
    }

    /**
     * Удаление чемпионата
     * @param id идентификатор
     */
    @Override
    public void delete(Object id) {
        Championship championship = findById(id);
        List<Club> clubs = clubRepository.findByChampionshipId(Long.valueOf(id.toString()));
        if (clubs.size() > 0) {
            throw new EntityHasDetailsException(Club.TYPE_NAME, championship.getId());
        }
        championshipRepository.deleteById(Long.valueOf(id.toString()));
    }

    /**
     * Обновление чемпионата
     * @param championship объект
     * @return Championship
     */
    @Override
    public Championship updateChampionship(Championship championship){

        if (championship == null){
            throw new EntityIllegalArgumentException("Объект не может быть пустым");
        }

        if (championship.getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта для создания не должен быть пустым");
        }
        Championship updChampionship = findById(championship.getId().toString());
        if (updChampionship == null){
            throw new EntityNotFoundException("Идентификатор объекта должен относится существующей сущности");
        }
        return championshipRepository.save(championship);
    }

}
