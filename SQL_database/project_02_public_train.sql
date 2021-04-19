create table train
(
    train_id      varchar(10) not null
        constraint train_pk
            primary key,
    type          varchar     not null,
    color         varchar     not null,
    "speed(km/h)" int4
);

create unique index train_train_id_uindex
    on train (train_id);
