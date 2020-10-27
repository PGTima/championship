package com.example.championship.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "championship")
public class Championship {
    public static String TYPE_NAME = "Чемпионат";
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "championship_id_seq")
    @SequenceGenerator(name = "championship_id_seq", sequenceName = "championship_id_seq", allocationSize = 1)
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
