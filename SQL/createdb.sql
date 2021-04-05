#Table creation
create table AGENCY (
name varchar(30),
street varchar(30) not null,
artists_num int not null,
primary key(name),
INDEX idx_street (street));

#Table creation
create table ARTIST (
name varchar(30),
member_num int not null,
debut_year int not null,
agency varchar(30),
primary key(name),
foreign key(agency) references AGENCY(name)
on delete cascade
on update cascade,
INDEX idx_debut_year (debut_year));

#Table creation
create table SONG (
song_id int,
title varchar(30),
artist varchar(30),
released_year int not null,
primary key(song_id),
foreign key(artist) references ARTIST(name)
on delete cascade
on update cascade,
INDEX idx_released_year (released_year));

#View creation
create view debut_released_2015 as
SELECT title, artist, released_year, debut_year
FROM SONG, ARTIST
WHERE artist=name and released_year >= 2015 and debut_year <= 2015;

#inserts for AGENCY relation
insert into AGENCY(name, street, artists_num)
values
('Ewha', 'Sinchon', 4),
('SM', 'Gangnam', 92),
('JYP', 'Gangdong', 47),
('EDAM', 'Gangnam', 1),
('amoeba_culture', 'Mapo', 11),
('CJ_E\&M', 'Mapo', 231),
('YG', 'Mapo', 60),
('RIAK', 'Mapo', 7),
('CUBE', 'Seongdong', 39);

#inserts for ARTIST relation
insert into ARTIST(name, member_num, debut_year, agency)
values
('Exception', 4, 2020, 'Ewha'),
('NCT127', 10, 2016, 'SM'),
('Wonder_Girls', 4, 2007, 'JYP'),
('TWICE', 9, 2015, 'JYP'),
('IU', 1, 2008, 'EDAM'),
('HA\:TFELT', 1, 2007, 'amoeba_culture'),
('Busker_Busker', 3, 2011, 'CJ_E\&M'),
('Red_Velvet', 5, 2014, 'SM'),
('AKMU', 2, 2014, 'YG'),
('P\-Peach', 1, 2010, 'RIAK'),
('NCT_DREAM', 7, 2016, 'SM');

#inserts for SONG relation
insert into SONG(song_id, title, artist, released_year)
values
(8621, 'Dandy_Tomato', 'Exception', 2020),
(7103, 'Kick_It', 'NCT127', 2020),
(1963, 'Why_so_lonely', 'Wonder_Girls', 2016),
(2751, 'LIKEY', 'TWICE', 2019),
(2013, 'Bug_Ending', 'Exception', 2020),
(1657, 'You_and_I', 'IU', 2011),
(3015, 'To_The_Beautiful_You', 'Wonder_Girls', 2016),
(4962, 'Satellite', 'HA\:TFELT', 2020),
(7813, 'Tell_Me', 'Wonder_Girls', 2007),
(9031, 'TT', 'TWICE', 2016),
(3675, 'Marshmallow', 'IU', 2009),
(5913, 'Yeosu_Night_Sea', 'Busker_Busker', 2012),
(1654, 'Red_Flavor', 'Red_Velvet', 2017),
(8031, 'Pluhmm', 'HA\:TFELT', 2018),
(1037, 'Calling_You', 'Busker_Busker', 2012),
(2220, 'Little_Star', 'AKMU', 2014),
(1330, 'To_The_Beautiful_You', 'P\-Peach', 2011);