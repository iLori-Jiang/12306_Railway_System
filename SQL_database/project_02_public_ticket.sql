create table ticket
(
    order_id          int4                                                     not null
        constraint ticket_order_order_id_fk
            references "order",
    travel_id         int4                                                     not null
        constraint ticket_travel_travel_id_fk
            references travel,
    depart_station_id int4                                                     not null
        constraint ticket_station_station_id_fk
            references station,
    arrive_station_id int4                                                     not null
        constraint ticket_station_station_id_fk_2
            references station,
    seat_type         varchar(20)                                              not null,
    ticket_price      numeric(10, 2)                                           not null,
    ticket_id         serial not null
        constraint ticket_pk
            primary key
);

create unique index ticket_ticket_id_uindex
    on ticket (ticket_id);
