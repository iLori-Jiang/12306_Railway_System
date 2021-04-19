create table train_seat
(
    train_id   varchar not null
        constraint train_seat_train_train_id_fk
            references train,
    seat_type  varchar not null,
    seat_count int4    not null
);
