create table station
(
    station_id   serial not null
        constraint station_pk
            primary key,
    station_name varchar(50)                                              not null
        constraint station_station_name_uindex
            unique,
    city_name    varchar(45)                                              not null
);

