package com.example.championship.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "club")
public class Club {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "club_id_seq")
    @SequenceGenerator(name = "club_id_seq", sequenceName = "club_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    private Championship championship;

    public Club(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Club(Long id, String name, String description, Championship championship) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.championship = championship;
    }
}
