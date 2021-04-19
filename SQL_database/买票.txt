create function buy_ticket(travelid integer, seat character varying, depart integer, arrive integer)
    returns TABLE(traveldeid integer, seatdetype character varying, seatdeleft integer, departdestation_id integer, arrivedestation_id integer)
    language plpgsql
as
$$
declare
    departtime timestamp;
    arrivetime timestamp;
begin
    departtime = (select depart_time from passing_station where travel_id = travelid and station_id = depart);
    arrivetime = (select arrive_time from passing_station where travel_id = travelid and station_id = arrive);

    return query
    select sub.travel_id, sub.seat_type, sub.seat_left, sub.depart_station_id, sub.arrive_station_id from (
    select sr.travel_id, sr.seat_type, sr.seat_left, sr.depart_station_id, sr.arrive_station_id, ps2.depart_time, ps.arrive_time from seat_remain sr
    join passing_station ps on sr.travel_id = ps.travel_id and sr.arrive_station_id = ps.station_id
    join passing_station ps2 on sr.travel_id = ps2.travel_id and sr.depart_station_id = ps2.station_id
    where sr.travel_id = travelid and sr.seat_type = seat
    order by arrive_time) sub
    where depart_time >= departtime
    and arrive_time <= arrivetime;
end;
$$;


