package com.example.championship.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "championship")
public class Championship {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "maxcountclub")
    private int maxCountClub;

    public Championship(Long id, String name, String description, int maxCountClub) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxCountClub = maxCountClub;
    }
}
