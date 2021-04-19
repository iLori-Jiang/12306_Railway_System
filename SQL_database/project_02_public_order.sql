create table "order"
(
    order_id     serial not null
        constraint order_pk
            primary key,
    id_card      varchar(18)                                            not null
        constraint order_user_id_card_fk
            references "user",
    create_date  timestamp(6)                                           not null,
    order_status varchar(1)                                             not null
);

