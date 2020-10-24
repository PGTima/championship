CREATE TABLE public.club
(
    id bigint NOT NULL,
    name text,
    description text,
    championship_id bigint NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.club
    OWNER to postgres;
-----------------------------------------------------------
CREATE TABLE public.championship
(
    id bigint NOT NULL,
    name text,
    description text,
    maxCountClub integer,
    PRIMARY KEY (id)
);

ALTER TABLE public.championship
    OWNER to postgres;

alter table club
	add constraint club_championship_id_fk
		foreign key (championship_id) references championship;