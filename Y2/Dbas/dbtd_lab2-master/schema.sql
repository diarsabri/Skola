CREATE EXTENSION btree_gist;

CREATE TABLE teams (
	team_id serial NOT NULL,
	team_name varchar(50) NOT NULL,
	team_removed boolean NOT NULL,
	CONSTRAINT teams_pk PRIMARY KEY (team_id)
);

CREATE TABLE staff (
	staff_id serial NOT NULL,
	staff_name varchar(50) NOT NULL,
	position varchar(50) NOT NULL,
	belongs_to_team integer REFERENCES teams(team_id) NOT NULL,
	staff_removed boolean NOT NULL,
	CONSTRAINT staff_pk PRIMARY KEY (staff_id)
);

CREATE TABLE business_partner (
	bp_id serial NOT NULL,
	bp_name varchar(50) NOT NULL,
	CONSTRAINT business_partners_pk PRIMARY KEY (bp_id)
);

CREATE TABLE resources (
	room_id serial NOT NULL,
	room_name varchar(50) NOT NULL,
	cost money NOT NULL,
	CONSTRAINT resources_pk PRIMARY KEY (room_id)
);

CREATE TABLE resource_has_facility (
	facility integer REFERENCES facilities(facility_id),
	room integer REFERENCES resources(room_id),
	CONSTRAINT rf_unique UNIQUE (facility,room)
);

CREATE TABLE meeting (
	meeting_id serial NOT NULL,
	room integer REFERENCES resources(room_id) NOT NULL,
	time_booked tsrange NOT NULL,
	bookee integer REFERENCES staff(staff_id) NOT NULL,
	CONSTRAINT meeting_pk PRIMARY KEY (meeting_id),
	EXCLUDE USING gist (room WITH =, time_booked WITH &&)
);

CREATE TABLE meeting_participant (
	meeting integer REFERENCES meeting(meeting_id),
	staff integer REFERENCES staff(staff_id),
	CONSTRAINT mp_unique UNIQUE (meeting, staff)
);

CREATE TABLE bp_meeting_participant (
	meeting integer REFERENCES meeting(meeting_id),
	business_partner integer REFERENCES business_partner(bp_id)
);

CREATE TABLE bp_staff_relation (
	staff integer REFERENCES staff(staff_id) NOT NULL,
	bp integer REFERENCES business_partner(bp_id) NOT NULL,
	CONSTRAINT bp_staff_unique UNIQUE (staff, bp)
);

CREATE TABLE facilities (
	facility_id serial NOT NULL,
	facility_name varchar(50) NOT NULL,
	CONSTRAINT fml PRIMARY KEY (facility_id)
);

INSERT INTO facilities (facility_name) VALUES ('TV');
INSERT INTO facilities (facility_name) VALUES ('Högtalare');
INSERT INTO facilities (facility_name) VALUES ('Projektor');
INSERT INTO facilities (facility_name) VALUES ('Discolampor');

INSERT INTO resource_has_facility VALUES (0, 30);
INSERT INTO resource_has_facility VALUES (1, 30);
INSERT INTO resource_has_facility VALUES (1, 32);
INSERT INTO resource_has_facility VALUES (2, 32);
INSERT INTO resource_has_facility VALUES (2, 35);

INSERT INTO staff VALUES (0,'Sara','Utvecklare',10, FALSE);
INSERT INTO staff VALUES (1,'Johan','Utvecklare',11, FALSE);
INSERT INTO staff VALUES (2,'Jesper','Manager',12, FALSE);
INSERT INTO staff VALUES (3,'Diar','Utvecklare',13, FALSE);
INSERT INTO staff VALUES (4,'Knasman','Uvecklare',14, FALSE);
INSERT INTO staff VALUES (5,'Knutsson','Försäljning',15, FALSE);
INSERT INTO staff VALUES (6,'Rickard','Försäljjning',16, FALSE);
INSERT INTO staff VALUES (7,'Z7','H7',17, FALSE);
INSERT INTO staff VALUES (8,'Z8','J8',18, FALSE);
INSERT INTO staff VALUES (9,'Z9','K9',19, FALSE);

INSERT INTO teams VALUES (10,'TEAM A', FALSE);
INSERT INTO teams VALUES (11,'TEAM B', FALSE);
INSERT INTO teams VALUES (12,'TEAM C', FALSE);
INSERT INTO teams VALUES (13,'TEAM D', FALSE);
INSERT INTO teams VALUES (14,'TEAM E', FALSE);
INSERT INTO teams VALUES (15,'TEAM Q', FALSE);
INSERT INTO teams VALUES (16,'TEAM W', FALSE);
INSERT INTO teams VALUES (17,'TEAM F', FALSE);
INSERT INTO teams VALUES (18,'TEAM P', FALSE);
INSERT INTO teams VALUES (19,'TEAM G', FALSE);

INSERT INTO business_partner VALUES (20,'AAA');
INSERT INTO business_partner VALUES (21,'BBB');
INSERT INTO business_partner VALUES (22,'CCC');
INSERT INTO business_partner VALUES (23,'DDD');
INSERT INTO business_partner VALUES (24,'EEE');
INSERT INTO business_partner VALUES (25,'FFF');
INSERT INTO business_partner VALUES (26,'GGG');
INSERT INTO business_partner VALUES (27,'HHH');
INSERT INTO business_partner VALUES (28,'III');
INSERT INTO business_partner VALUES (29,'JJJ');

INSERT INTO bp_staff_relation VALUES (0,20);
INSERT INTO bp_staff_relation VALUES (1,21);
INSERT INTO bp_staff_relation VALUES (2,22);
INSERT INTO bp_staff_relation VALUES (3,23);
INSERT INTO bp_staff_relation VALUES (4,24);
INSERT INTO bp_staff_relation VALUES (0,25);
INSERT INTO bp_staff_relation VALUES (1,26);
INSERT INTO bp_staff_relation VALUES (2,27);
INSERT INTO bp_staff_relation VALUES (3,28);
INSERT INTO bp_staff_relation VALUES (4,29);

INSERT INTO resources VALUES (30,'AB',100);
INSERT INTO resources VALUES (31,'CD',150);
INSERT INTO resources VALUES (32,'EF',200);
INSERT INTO resources VALUES (33,'GH',250);
INSERT INTO resources VALUES (34,'IJ',300);
INSERT INTO resources VALUES (35,'KL',350);
INSERT INTO resources VALUES (36,'MN',400);
INSERT INTO resources VALUES (37,'OP',450);
INSERT INTO resources VALUES (38,'QR',500);
INSERT INTO resources VALUES (39,'ST',550);

INSERT INTO meeting VALUES (40,30,'[2018-01-01 14:30, 2018-01-01 15:30]',0);
INSERT INTO meeting VALUES (41,31,'[2018-01-02 14:30, 2018-01-02 15:30]',1);
INSERT INTO meeting VALUES (42,32,'[2018-01-03 14:30, 2018-01-03 15:30]',2);
INSERT INTO meeting VALUES (43,33,'[2018-01-04 14:30, 2018-01-04 15:30]',3);
INSERT INTO meeting VALUES (44,34,'[2018-01-05 14:30, 2018-01-05 15:30]',4);
INSERT INTO meeting VALUES (45,35,'[2018-01-06 14:30, 2018-01-06 15:30]',5);
INSERT INTO meeting VALUES (46,36,'[2018-01-07 14:30, 2018-01-07 15:30]',6);
INSERT INTO meeting VALUES (47,37,'[2018-01-08 14:30, 2018-01-08 15:30]',7);
INSERT INTO meeting VALUES (48,38,'[2018-01-09 14:30, 2018-01-09 15:30]',8);
INSERT INTO meeting VALUES (49,39,'[2018-01-10 14:30, 2018-01-10 15:30]',9);
INSERT INTO meeting VALUES (50,30,'[2018-01-10 14:30, 2018-01-10 15:30]',9);


/*
00-09 : Staff
10-19 : Teams
20-29 : Business_partners
30-39 : Resources
40-++ : Meetings
*/