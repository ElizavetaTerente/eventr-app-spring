INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, EMAIL, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'system', 'system', '$2a$10$nyIiPpCyHPTUFEyzAFD3VOtV5GGhviK7rWaJmZSaU0U.l04BEzYLG', 'system', 'thomas.plank.mobile@gmail.com', 'system', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('system', 'ADMIN')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('system', 'USER')

INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, EMAIL, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'admin', 'admin', '$2a$10$nyIiPpCyHPTUFEyzAFD3VOtV5GGhviK7rWaJmZSaU0U.l04BEzYLG', 'admin', 'thomas.plank.mobile@gmail.com', 'system', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('admin', 'ADMIN')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('admin', 'USER')

INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, EMAIL, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'manager', 'manager', '$2a$10$nyIiPpCyHPTUFEyzAFD3VOtV5GGhviK7rWaJmZSaU0U.l04BEzYLG', 'manager', 'thomas.plank.mobile@gmail.com', 'system', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('manager', 'MANAGER')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('manager', 'USER')

INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, EMAIL, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Alexandra', 'Jaeger', '$2a$10$nyIiPpCyHPTUFEyzAFD3VOtV5GGhviK7rWaJmZSaU0U.l04BEzYLG', 'alexandra', 'Alexandra.Jaeger@uibk.ac.at', 'system', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('alexandra', 'USER')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('alexandra', 'ADMIN')

INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, EMAIL, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Thomas', 'Plank', '$2a$10$nyIiPpCyHPTUFEyzAFD3VOtV5GGhviK7rWaJmZSaU0U.l04BEzYLG', 'plank', 'thomas.plank.mobile@gmail.com', 'system', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('plank', 'ADMIN')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('plank', 'USER')

INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, EMAIL, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Andreas', 'Huber', '$2a$10$nyIiPpCyHPTUFEyzAFD3VOtV5GGhviK7rWaJmZSaU0U.l04BEzYLG', 'andi', 'andi.ah91@gmail.com', 'system', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('andi', 'ADMIN')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('andi', 'USER')

INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, EMAIL, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Elizaveta', 'Terente', '$2a$10$nyIiPpCyHPTUFEyzAFD3VOtV5GGhviK7rWaJmZSaU0U.l04BEzYLG', 'liza', 'veronika.viskey@gmail.com', 'system', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('liza', 'ADMIN')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('liza', 'USER')

INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, EMAIL, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Test', 'User', '$2a$10$nyIiPpCyHPTUFEyzAFD3VOtV5GGhviK7rWaJmZSaU0U.l04BEzYLG', 'test_user', 'test.email@gmail.com', 'system', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('test_user', 'USER')

INSERT INTO LOCATION (NAME, DESCRIPTION, URL, CREATE_DATE, CREATE_USER_USERNAME, STREET, CITY, ACTIVE) VALUES ('9b', 'Bar mit Küche in Uninähe', 'http://www.neunbe.at', '2016-01-01 00:00:00', 'plank', 'Technikerstrasse+9b', 'Innsbruck', 'false')
INSERT INTO LOCATION (NAME, DESCRIPTION, URL, CREATE_DATE, CREATE_USER_USERNAME, STREET, CITY, ACTIVE) VALUES ('Mensa Technik', 'Restaurant am Uni-Campus', 'https://www.mensen.at', '2016-01-01 00:00:00', 'plank', 'Technikerstrasse+13', 'Innsbruck', 'true')
INSERT INTO LOCATION (NAME, DESCRIPTION, URL, CREATE_DATE, CREATE_USER_USERNAME, STREET, CITY, ACTIVE) VALUES ('Ruetz', 'Bäckerei neben Mpreis', 'https://www.ruetz.at',  '2016-01-01 00:00:00', 'plank', 'Viktor-Franz-Hess-Straße+3', 'Innsbruck', 'true')
INSERT INTO LOCATION (NAME, DESCRIPTION, URL, CREATE_DATE, CREATE_USER_USERNAME, STREET, CITY, ACTIVE) VALUES ('Bamboo', 'ChinaRestautrant Innsbruck', 'https://bamboo-innsbruck.eatbu.com/?lang=de#menu',  '2016-01-01 00:00:00', 'plank', 'Kirschentalgasse+8', 'Innsruck', 'true')
INSERT INTO LOCATION (NAME, DESCRIPTION, URL, CREATE_DATE, CREATE_USER_USERNAME, STREET, CITY, ACTIVE) VALUES ('UnaPizza', 'Die echte neapolitanische Pizza in Innsbruck', 'https://unapizza.at/',  '2016-01-01 00:00:00', 'plank', 'Universitätsstrasse+3+Top+1', 'Innsbruck', 'true')
INSERT INTO LOCATION (NAME, DESCRIPTION, URL, CREATE_DATE, CREATE_USER_USERNAME, STREET, CITY, ACTIVE) VALUES ('Machete', 'Taco-Kartell Innsbruck', 'https://www.machete-burritos.com/foodandmore',  '2016-01-01 00:00:00', 'plank', 'Anichstrasse+29', 'Innsbruck', 'true')

INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('9b', false, 'MO', '10:00', '20:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('9b', true, 'DI', '9:00', '20:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('9b', true, 'MI', '9:00', '20:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('9b', true, 'DO', '9:00', '20:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('9b', true, 'FR', '9:00', '20:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('9b', true, 'SA', '9:00', '20:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('9b', true, 'SO', '9:00', '20:00')

INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Ruetz', true, 'MO', '10:00', '18:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Ruetz', true, 'DI', '10:00', '18:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Ruetz', true, 'MI', '9:00', '18:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Ruetz', true, 'DO', '10:00', '18:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Ruetz', true, 'FR', '9:00', '18:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Ruetz', true, 'SA', '10:00', '18:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Ruetz', true, 'SO', '9:00', '18:00')

INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Mensa Technik', true, 'MO', '9:00', '15:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Mensa Technik', true, 'DI', '9:00', '15:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Mensa Technik', true, 'MI', '9:00', '15:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Mensa Technik', true, 'DO', '9:00', '15:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Mensa Technik', true, 'FR', '9:00', '13:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Mensa Technik', true, 'SA', '9:00', '13:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Mensa Technik', true, 'SO', '9:00', '13:00')

INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Bamboo', false, 'MO', '9:00', '15:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Bamboo', true, 'DI', '9:00', '18:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Bamboo', false, 'MI', '9:00', '15:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Bamboo', true, 'DO', '9:00', '18:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Bamboo', true, 'FR', '9:00', '20:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Bamboo', true, 'SA', '9:00', '20:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Bamboo', true, 'SO', '9:00', '20:00')

INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('UnaPizza', true, 'MO', '9:00', '15:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('UnaPizza', true, 'DI', '9:00', '15:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('UnaPizza', false, 'MI', '9:00', '15:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('UnaPizza', true, 'DO', '9:00', '18:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('UnaPizza', true, 'FR', '9:00', '20:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('UnaPizza', true, 'SA', '9:00', '20:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('UnaPizza', true, 'SO', '9:00', '20:00')

INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Machete', false, 'MO', '9:00', '15:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Machete', true, 'DI', '11:30', '23:59')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Machete', false, 'MI', '9:00', '15:00')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Machete', true, 'DO', '11:30', '23:59')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Machete', true, 'FR', '11:30', '23:59')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Machete', true, 'SA', '11:30', '23:59')
INSERT INTO LOCATION_OPENING_HOURS(NAME, OPENED, WEEKDAY, OPEN, CLOSE) VALUES ('Machete', true, 'SO', '11:30', '23:59')

INSERT INTO TAG(NAME, CREATE_DATE, CREATE_USER_USERNAME) VALUES('Backwaren', '2016-01-01 00:00:00', 'plank')
INSERT INTO TAG(NAME, CREATE_DATE, CREATE_USER_USERNAME) VALUES('Tagessuppe', '2016-01-01 00:00:00', 'plank')
INSERT INTO TAG(NAME, CREATE_DATE, CREATE_USER_USERNAME) VALUES('familienfreundlich', '2016-01-01 00:00:00', 'plank')
INSERT INTO TAG(NAME, CREATE_DATE, CREATE_USER_USERNAME) VALUES('chinesisch', '2016-01-01 00:00:00', 'plank')
INSERT INTO TAG(NAME, CREATE_DATE, CREATE_USER_USERNAME) VALUES('Pizza', '2016-01-01 00:00:00', 'plank')
INSERT INTO TAG(NAME, CREATE_DATE, CREATE_USER_USERNAME) VALUES('Burritos', '2016-01-01 00:00:00', 'plank')
INSERT INTO TAG(NAME, CREATE_DATE, CREATE_USER_USERNAME) VALUES('Alkohol', '2016-01-01 00:00:00', 'plank')

INSERT INTO LOCATION_TAGS (LOCATION_NAME, TAG_NAME) VALUES ('9b','Tagessuppe')
INSERT INTO LOCATION_TAGS (LOCATION_NAME, TAG_NAME) VALUES ('9b','Alkohol')
INSERT INTO LOCATION_TAGS (LOCATION_NAME, TAG_NAME) VALUES ('Ruetz','Backwaren')
INSERT INTO LOCATION_TAGS (LOCATION_NAME, TAG_NAME) VALUES ('Mensa Technik','Tagessuppe')
INSERT INTO LOCATION_TAGS (LOCATION_NAME, TAG_NAME) VALUES ('Bamboo','chinesisch')
INSERT INTO LOCATION_TAGS (LOCATION_NAME, TAG_NAME) VALUES ('Bamboo','Tagessuppe')
INSERT INTO LOCATION_TAGS (LOCATION_NAME, TAG_NAME) VALUES ('UnaPizza','Pizza')
INSERT INTO LOCATION_TAGS (LOCATION_NAME, TAG_NAME) VALUES ('Machete','Alkohol')
INSERT INTO LOCATION_TAGS (LOCATION_NAME, TAG_NAME) VALUES ('Machete','Burritos')

INSERT INTO VOTING (NAME, VOTING_ACTIVE, CREATE_USER_USERNAME, VOTING_STOP, CREATE_DATE) VALUES ('Ablschlussessen', 'TRUE', 'andi', '2023-05-05 15:00:00', '2016-01-01 00:00:00')
INSERT INTO VOTING (NAME, VOTING_ACTIVE, CREATE_USER_USERNAME, VOTING_STOP, CREATE_DATE) VALUES ('Morgensemmel 13', 'TRUE', 'andi', '2016-01-01 00:00:00', '2016-01-01 00:00:00')
INSERT INTO VOTING (NAME, VOTING_ACTIVE, CREATE_USER_USERNAME, VOTING_STOP, CREATE_DATE) VALUES ('party', 'TRUE', 'plank', '2023-01-01 00:00:00', '2016-01-01 00:00:00')

INSERT INTO VOTING_PARTICIPANTS  (VOTING_NAME, PARTICIPANT_NAME) VALUES ('Ablschlussessen', 'andi')
INSERT INTO VOTING_PARTICIPANTS  (VOTING_NAME, PARTICIPANT_NAME) VALUES ('Ablschlussessen', 'plank')
INSERT INTO VOTING_PARTICIPANTS  (VOTING_NAME, PARTICIPANT_NAME) VALUES ('Ablschlussessen', 'liza')

INSERT INTO VOTING_PARTICIPANTS  (VOTING_NAME, PARTICIPANT_NAME) VALUES ('Morgensemmel 13', 'andi')
INSERT INTO VOTING_PARTICIPANTS  (VOTING_NAME, PARTICIPANT_NAME) VALUES ('Morgensemmel 13', 'plank')
INSERT INTO VOTING_PARTICIPANTS  (VOTING_NAME, PARTICIPANT_NAME) VALUES ('Morgensemmel 13', 'alexandra')

INSERT INTO VOTING_PARTICIPANTS  (VOTING_NAME, PARTICIPANT_NAME) VALUES ('party', 'andi')
INSERT INTO VOTING_PARTICIPANTS  (VOTING_NAME, PARTICIPANT_NAME) VALUES ('party', 'plank')
INSERT INTO VOTING_PARTICIPANTS  (VOTING_NAME, PARTICIPANT_NAME) VALUES ('party', 'alexandra')

INSERT INTO VOTING_POSSIBILITIES (VOTING_NAME, POSSIBILITY_NAME) VALUES ('Ablschlussessen', 'Machete')
INSERT INTO VOTING_POSSIBILITIES (VOTING_NAME, POSSIBILITY_NAME) VALUES ('Ablschlussessen', 'UnaPizza')
INSERT INTO VOTING_POSSIBILITIES (VOTING_NAME, POSSIBILITY_NAME) VALUES ('Ablschlussessen', 'Bamboo')

INSERT INTO VOTING_POSSIBILITIES (VOTING_NAME, POSSIBILITY_NAME) VALUES ('Morgensemmel 13', 'Ruetz')

INSERT INTO VOTING_POSSIBILITIES (VOTING_NAME, POSSIBILITY_NAME) VALUES ('party', 'Machete')
INSERT INTO VOTING_POSSIBILITIES (VOTING_NAME, POSSIBILITY_NAME) VALUES ('party', 'UnaPizza')
INSERT INTO VOTING_POSSIBILITIES (VOTING_NAME, POSSIBILITY_NAME) VALUES ('party', 'Bamboo')

INSERT INTO VOTING_TIMESLOTS (VOTING_NAME, START, END) VALUES ('Ablschlussessen', '2023-01-01 00:00:00', '2023-01-01 01:00:00' )
INSERT INTO VOTING_TIMESLOTS (VOTING_NAME, START, END) VALUES ('Ablschlussessen', '2023-01-01 02:00:00', '2023-01-01 04:00:00' )

INSERT INTO VOTING_TIMESLOTS (VOTING_NAME, START, END) VALUES ('Morgensemmel 13', '2022-06-06 06:00:00', '20232-06-06 06:00:00' )

INSERT INTO VOTING_TIMESLOTS (VOTING_NAME, START, END) VALUES ('party', '2023-01-02 10:00:00', '2023-01-02 11:00:00' )
INSERT INTO VOTING_TIMESLOTS (VOTING_NAME, START, END) VALUES ('party', '2023-01-02 11:00:00', '2023-01-02 12:00:00' )
INSERT INTO VOTING_TIMESLOTS (VOTING_NAME, START, END) VALUES ('party', '2023-01-02 10:00:00', '2023-01-02 12:00:00' )

INSERT INTO EVENT (LOCATION_NAME, NAME, CREATE_DATE, START, END, CREATE_USER_USERNAME) VALUES ('9b', 'Essen ArbeitsGruppe Software-Architecktur', '2016-01-01 00:00:00', '2022-01-24 12:00:00', '2022-02-24 13:00:00', 'plank')
INSERT INTO EVENT (LOCATION_NAME, NAME, CREATE_DATE, START, END, CREATE_USER_USERNAME) VALUES ('Ruetz', 'Dinner fo One', '2016-01-01 00:00:00', '2021-12-31 23:35:00', '2021-12-31 23:55:00', 'plank')
INSERT INTO EVENT (LOCATION_NAME, NAME, CREATE_DATE, START, END, CREATE_USER_USERNAME) VALUES ('9b', 'Morgensemmel 12', '2016-01-01 00:00:00', '2022-01-24 09:00:00', '2022-01-24 10:00:00', 'plank')
INSERT INTO EVENT (LOCATION_NAME, NAME, CREATE_DATE, START, END, CREATE_USER_USERNAME) VALUES ('Ruetz', 'Test4', '2016-01-01 00:00:00', '2022-01-07 00:00:00', '2022-01-07 01:00:00', 'plank')
INSERT INTO EVENT (LOCATION_NAME, NAME, CREATE_DATE, START, END, CREATE_USER_USERNAME) VALUES ('UnaPizza', 'pizzatime', '2016-01-01 00:00:00', '2022-03-03 18:00:00', '2022-03-03 19:00:00', 'andi')
INSERT INTO EVENT (LOCATION_NAME, NAME, CREATE_DATE, START, END, CREATE_USER_USERNAME) VALUES ('Machete', 'CARNEval', '2016-01-01 00:00:00', '2022-04-01 12:00:00', '2022-04-01 14:00:00', 'plank')
INSERT INTO EVENT (LOCATION_NAME, NAME, CREATE_DATE, START, END, CREATE_USER_USERNAME) VALUES ('Ruetz', 'Morgensemmel 13', '2016-01-01 00:00:00', '2022-02-24 09:00:00', '2022-02-24 10:00:00', 'andi')

INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('Essen ArbeitsGruppe Software-Architecktur', 'andi')
INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('Essen ArbeitsGruppe Software-Architecktur', 'plank')
INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('Essen ArbeitsGruppe Software-Architecktur', 'liza')
INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('Essen ArbeitsGruppe Software-Architecktur', 'alexandra')

INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('Dinner fo One', 'andi')

INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('Morgensemmel 12', 'plank')
INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('Morgensemmel 12', 'andi')

INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('CARNEval', 'andi')
INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('CARNEval', 'liza')
INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('CARNEval', 'alexandra')

INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('Test4', 'andi')
INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('Test4', 'plank')

INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('pizzatime', 'andi')
INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('pizzatime', 'plank')
INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('pizzatime', 'liza')
INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('pizzatime', 'alexandra')

INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('Morgensemmel 13', 'plank')
INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('Morgensemmel 13', 'andi')
INSERT INTO EVENT_PARTICIPANTS (EVENT_NAME, PARTICIPANT_NAME) VALUES ('Morgensemmel 13', 'alexandra')