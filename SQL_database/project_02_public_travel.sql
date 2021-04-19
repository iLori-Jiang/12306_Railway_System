create table travel
(
    travel_id serial
        constraint travel_pk
            primary key,
    train_id  varchar not null
        constraint travel_train_train_id_fk
            references train
);

create unique index travel_travel_id_uindex
    on travel (travel_id);

-- INSERT INTO public.travel (travel_id, train_id) VALUES (2, 'D944');
-- INSERT INTO public.travel (travel_id, train_id) VALUES (3, 'G6012');
-- INSERT INTO public.travel (travel_id, train_id) VALUES (4, 'C7110');
-- INSERT INTO public.travel (travel_id, train_id) VALUES (5, 'G280');
-- INSERT INTO public.travel (travel_id, train_id) VALUES (6, 'D7566');
-- INSERT INTO public.travel (travel_id, train_id) VALUES (7, 'G76');