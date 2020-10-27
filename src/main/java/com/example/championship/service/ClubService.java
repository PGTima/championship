package com.example.championship.service;

import com.example.championship.exceptions.EntityAlreadyExistsException;
import com.example.championship.exceptions.EntityHasDetailsException;
import com.example.championship.exceptions.EntityIllegalArgumentException;
import com.example.championship.exceptions.EntityNotFoundException;
import com.example.championship.jpaRepository.ChampionshipRepository;
import com.example.championship.jpaRepository.ClubRepository;
import com.example.championship.model.Championship;
import com.example.championship.model.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final ChampionshipRepository championshipRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository, ChampionshipRepository championshipRepository) {
        this.clubRepository = clubRepository;
        this.championshipRepository = championshipRepository;
    }
    public List<Club> findAll(){
        return clubRepository.findAll();
    }
    /**
     * Поиск клуба по id
     * @param id
     * @return Club
     */
    public Club findById(Object id){
        Club club;
        if(id == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть пустым");
        }
        Long parsedId;
        try{
            parsedId = Long.valueOf((String) id);
        } catch(NumberFormatException ex){
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификкатор к  нужному типу, текст ошибки %s", ex));
        }
        club = clubRepository.findById(parsedId).orElse(null);
        if (club == null){
            throw new EntityNotFoundException(Club.TYPE_NAME,parsedId);
        }
        return club;
    }

    /**
     * Поиск клуба по названию
     * @param name
     * @return Club
     */
    public Club findByName(String name){
        Club club;
        if(name == null) {
            throw new EntityIllegalArgumentException("Имя объекта не передано");
        }
        if(name.isEmpty()) {
            throw new EntityIllegalArgumentException("Имя объекта не может быть пустым");
        }
        club = clubRepository.findByName(name);
        return club;
    }

    /**
     * Поиск клубов по id чемпионата
     * @param id
     * @return List Club
     */
    public List<Club> findByClubByChampioshipId(Object id){
        List<Club> club;
        if(id == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть пустым");
        }
        Long parsedId;
        try{
            parsedId = Long.valueOf((String) id);
        } catch(NumberFormatException ex){
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификкатор к  нужному типу, текст ошибки %s", ex));
        }
        Championship championship = championshipRepository.findById(parsedId).orElse(null);
        if (championship == null){
            throw new EntityNotFoundException(Championship.TYPE_NAME,parsedId);
        }

        club = clubRepository.findByChampionshipId(championship.getId());

        return club;
    }

    /**
     * Создание клуба
     * @param club объект
     * @return Club
     */
    public Club createClub(Club club){

        if (club == null){
            throw new EntityIllegalArgumentException("Объект не может быть пустым");
        }
        Championship championship = championshipRepository.findById(club.getChampionship().getId()).orElse(null);

        if (championship == null){
            throw new EntityIllegalArgumentException("Поле чемпионат у клуба должно быть обязательно заполнено");
        }

        if(findByName(club.getName()) != null){
            throw new EntityAlreadyExistsException(Club.TYPE_NAME, club.getName());
        }

        List<Club> clubs = findByClubByChampioshipId(championship.getId().toString());
        if (clubs.size() >= championship.getMaxCountClub()){
            throw new EntityHasDetailsException("Количество клубов в чемпионате не может быть больше допустимого", championship.getMaxCountClub());
        }
        return clubRepository.save(club);
    }

    /**
     * Удаление клуба
     * @param id идентификатор
     */
    public void delete(Object id) {
        Club club = findById(id);
        championshipRepository.deleteById(Long.valueOf(id.toString()));
    }

    /**
     * Обновление клуба
     * @param club объект
     * @return Club
     */
    public Club updateClub(Club club){

        if (club == null){
            throw new EntityIllegalArgumentException("Объект не может быть пустым");
        }

        if (club.getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта для создания не должен быть пустым");
        }
        Championship championship = championshipRepository.findById(club.getChampionship().getId()).orElse(null);

        if (championship == null){
            throw new EntityIllegalArgumentException("Поле чемпионат у клуба должно быть обязательно заполнено");
        }
        List<Club> clubs = findByClubByChampioshipId(championship.getId().toString());
        if (clubs.size() > championship.getMaxCountClub()){
            throw new EntityHasDetailsException("Количество клубов в чемпионате не может быть больше допустимого", championship.getMaxCountClub());
        }
        return clubRepository.save(club);
    }

}
