CREATE TABLE public.club
(
    id SERIAL NOT NULL,
    name text,
    description text,
    championship_id bigint NOT NULL,
    PRIMARY KEY (id)
);
-----------------------------------------------------------
CREATE TABLE public.championship
(
    id SERIAL NOT NULL,
    name text,
    description text,
    maxCountClub integer,
    PRIMARY KEY (id)
);
CREATE SEQUENCE public.championship_id_seq;
CREATE SEQUENCE public.club_id_seq;

alter table club
	add constraint club_championship_id_fk
		foreign key (championship_id) references championship;