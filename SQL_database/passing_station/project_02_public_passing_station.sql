create table passing_station
(
    travel_id   int4 not null
        constraint passing_station_travel_travel_id_fk
            references travel,
    station_id  int4 not null
        constraint passing_station_station_station_id_fk
            references station,
    arrive_time timestamp(2),
    depart_time timestamp(2)
);

alter table passing_station add column distance int not null default -1;

-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (2, 9, '2020-05-11 20:52:00.00', '2020-05-11 20:54:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (2, 1, '2020-05-12 06:27:00.00', '2020-05-12 06:31:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (2, 4, '2020-05-12 07:03:00.00', '2020-05-12 07:24:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (2, 8, '2020-05-11 19:55:00.00', '2020-05-11 19:55:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (2, 10, '2020-05-12 08:33:00.00', '2020-05-12 08:33:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (3, 1, '2020-05-17 07:08:00.00', '2020-05-17 07:08:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (3, 7, '2020-05-17 07:25:00.00', '2020-05-17 07:31:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (3, 4, '2020-05-17 07:48:00.00', '2020-05-17 07:54:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (3, 6, '2020-05-17 10:35:00.00', '2020-05-17 10:35:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (4, 2, '2020-05-17 07:19:00.00', '2020-05-17 07:19:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (4, 5, '2020-05-17 08:05:00.00', '2020-05-17 08:07:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (4, 3, '2020-05-17 08:41:00.00', '2020-05-17 08:44:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (4, 11, '2020-05-17 08:54:00.00', '2020-05-17 08:54:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (5, 1, '2020-05-17 07:42:00.00', '2020-05-17 07:42:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (5, 4, '2020-05-17 08:12:00.00', '2020-05-17 08:15:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (5, 6, '2020-05-17 10:58:00.00', '2020-05-17 11:02:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (5, 12, '2020-05-17 12:27:00.00', '2020-05-17 12:30:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (5, 13, '2020-05-17 14:37:00.00', '2020-05-17 14:42:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (5, 14, '2020-05-17 18:13:00.00', '2020-05-17 18:13:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (6, 2, '2020-05-17 11:46:00.00', '2020-05-17 11:46:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (6, 5, '2020-05-17 12:28:00.00', '2020-05-17 12:30:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (6, 3, '2020-05-17 13:05:00.00', '2020-05-17 13:08:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (6, 11, '2020-05-17 13:18:00.00', '2020-05-17 13:24:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (6, 15, '2020-05-17 13:48:00.00', '2020-05-17 13:48:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (7, 1, '2020-05-17 15:47:00.00', '2020-05-17 15:47:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (7, 7, '2020-05-17 16:04:00.00', '2020-05-17 16:06:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (7, 4, '2020-05-17 16:23:00.00', '2020-05-17 16:28:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (7, 6, '2020-05-17 19:11:00.00', '2020-05-17 19:15:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (7, 12, '2020-05-17 20:34:00.00', '2020-05-17 20:41:00.00');
-- INSERT INTO public.passing_station (travel_id, station_id, arrive_time, depart_time) VALUES (7, 13, '2020-05-17 22:47:00.00', '2020-05-17 22:47:00.00');