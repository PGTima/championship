INSERT INTO championship(id, name, description, maxCountClub)
	VALUES (1, 'РФПЛ', 'Росси́йская Премье́р-Ли́га — профессиональная футбольная лига, высший дивизион в системе футбольных лиг России. В ней выступают 16 клубов. С момента основания называлась «Российская футбольная премьер-лига». В июле 2018 года была переименована в Российскую Премьер-Лигу с представлением нового логотипа', 2);
INSERT INTO championship(id, name, description, maxCountClub)
	VALUES (2, 'Бундес лига', 'Неме́цкая футбо́льная Бу́ндеслига — профессиональная футбольная лига для немецких футбольных клубов. Является высшим дивизионом в системе футбольных лиг Германии. В ней выступают 18 клубов. Чемпионат проходит с августа по май, каждая команда проводит 34 матча', 18);
INSERT INTO championship(id, name, description, maxCountClub)
	VALUES (3, 'Ла-лига', 'Приме́ра Дивисьо́н профессиональной футбольной лиги Испании, известный также как Примера и Ла Лига — профессиональный футбольный турнир высшего уровня в системе футбольных лиг Испании. Ла Лига считается одной из сильнейших лиг в мире', 20);
INSERT INTO championship(id, name, description, maxCountClub)
	VALUES (4, 'Лига 1', 'Лига 1 в сезоне 2018/2019 — 81-й сезон Лиги 1, высшего дивизиона в системе футбольных лиг Франции. Сезон начался 10 августа 2018 года и завершился 25 мая 2019 года. Чемпионский титул защитил клуб «Пари Сен-Жермен».', 20);
INSERT INTO championship(id, name, description, maxCountClub)
	VALUES (5, 'Серия А', 'Се́рия A — высший дивизион итальянской футбольной лиги, является одним из самых сильных футбольных дивизионов в мире, занимая в рейтинге УЕФА 4 место после чемпионатов Испании, Англии и Германии. Официальный спонсор — Telecom Italia Mobile. Отсюда и название — итал. Lega Nazionale Professionisti Serie A TIM.', 20);

INSERT INTO club(id, name, description, championship_id) VALUES (1, 'Спартак', 'Спартак Москва', 1);
INSERT INTO club(id, name, description, championship_id) VALUES (2, 'Зенит', 'Клуб из Санкт-Петербурга', 1);
INSERT INTO club(id, name, description, championship_id) VALUES (3, 'Гамбург', 'Немецкий клуб', 2);
INSERT INTO club(id, name, description, championship_id) VALUES (4, 'ПСЖ', 'Paris Saint German', 4);
INSERT INTO club(id, name, description, championship_id) VALUES (5, 'Ювентус', 'Италия', 5);

alter sequence club_id_seq restart with 6;
alter sequence championship_id_seq restart with 6;