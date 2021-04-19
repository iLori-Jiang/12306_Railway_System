create function travel_consult(id integer)
    returns TABLE(train_id character varying, type character varying, station character varying, arrivetime timestamp without time zone, departtime timestamp without time zone)
    language plpgsql
as
$$
BEGIN
    return query
    select T.train_id, T2.type, station_name, arrive_time, depart_time
    from travel T
    join train T2 on T.train_id = T2.train_id
    join passing_station Ps on T.travel_id = Ps.travel_id
    join station S on Ps.station_id = S.station_id
    where T.travel_id = id
    order by depart_time;

end;
$$;


