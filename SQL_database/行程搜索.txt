create function travel_search(depart_city character varying, arrive_city character varying, departdate character varying)
    returns TABLE(travel_id integer, depart_station_id integer, depart_time timestamp without time zone, arrive_station_id integer, arrive_time timestamp without time zone)
    language plpgsql
as
$$
begin

return query
    select ps1.travel_id, ps1.station_id, ps1.depart_time, ps2.station_id, ps2.arrive_time
    from passing_station ps1
    join passing_station ps2 on ps1.travel_id = ps2.travel_id
    join (select station_id from station where city_name = depart_city) depart_station on ps1.station_id = depart_station.station_id
    join (select station_id from station where city_name = arrive_city) arrive_station on ps2.station_id = arrive_station.station_id
    and ps1.depart_time >= cast((departDate || ' 00:00') as timestamp)
    and ps1.depart_time < cast((substring(departDate,1,9) || (cast(substring(departDate,10,1) as integer) +1) || ' 00:00') as timestamp)
    and ps1.depart_time < ps2.arrive_time;
end;
$$;


