create table "user"
(
    first_name   varchar(30) not null,
    last_name    varchar(50) not null,
    phone_number varchar(20) not null
        constraint user_phone_number_uindex
            unique,
    id_card      varchar(18) not null
);

create unique index user_id_card_uindex
    on "user" (id_card);

create unique index user_pk
    on "user" (id_card);

alter table "user"
    add constraint user_pk
        primary key (id_card);

--INSERT INTO public."user" (first_name, last_name, phone_number, id_card) VALUES ('江', '海洋', '13610192953', '440106199901144412');
--INSERT INTO public."user" (first_name, last_name, phone_number, id_card) VALUES ('赵', '奕帆', '13322815789', '31011719250712199X');
--INSERT INTO public."user" (first_name, last_name, phone_number, id_card) VALUES ('李', '博宣', '18922268029', '130631190002140071');
--INSERT INTO public."user" (first_name, last_name, phone_number, id_card) VALUES ('江', '海洋', '110', '110');