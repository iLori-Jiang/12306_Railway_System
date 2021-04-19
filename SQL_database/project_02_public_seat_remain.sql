create table seat_remain
(
    travel_id         int4           not null
        constraint seat_remain_travel_travel_id_fk
            references travel,
    seat_type         varchar        not null,
    seat_left         int4           not null,
    depart_station_id int4           not null
        constraint seat_remain_station_station_id_fk
            references station,
    arrive_station_id int4           not null
        constraint seat_remain_station_station_id_fk_2
            references station,
    price             numeric(10, 2) not null
);

