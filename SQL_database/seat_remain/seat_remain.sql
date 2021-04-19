create or replace function calculate_price(seat_type varchar, distance int)
    returns numeric(10, 2)
as
$$
declare
    base   double precision;
    result double precision;
    range  double precision;
begin
    base = distance * 0.05861 * 1.25;
    if (distance between 0 and 40) then
        range = 1;
    elseif (distance between 40 and 100) then
        range = 0.9;
    elseif (distance between 100 and 200) then
        range = 0.8;
    elseif (distance between 200 and 400) then
        range = 0.7;
    elseif (distance between 400 and 500) then
        range = 0.6;
    else
        range = 0.5;
    end if;
    case seat_type
        when '软卧' then result = base * range * 1.1::numeric(10, 2);
        when '硬卧' then result = base * range * 0.9::numeric(10, 2);
        when '硬座' then result = base * range * 0.6::numeric(10, 2);
        when '二等座' then result = base * range * 2.0::numeric(10, 2);
        when '一等座' then result = base * range * 2.8::numeric(10, 2);
        when '商务座' then result = base * range * 3.7::numeric(10, 2);
        end case;
    if (result >= 1 and result <= 500) then
        return result;
    elseif (result < 1) then
        return 1;
    else
        return 500;
    end if;
end;
$$
    language plpgsql;

select ps1.travel_id,
       ps1.station_id as depart_station_id,
       ps2.station_id as arrive_station_id,
       ps1.distance
from passing_station ps1
         inner join passing_station ps2
                    on ps1.depart_time = ps2.arrive_time
                        and ps1.travel_id = ps2.travel_id
                        and ps1.station_id <> ps2.station_id;


insert into seat_remain
    (select in3.travel_id,
            seat_type.seat_type,
            seat_count                                         as seat_left,
            depart_station_id,
            arrive_station_id,
            calculate_price(seat_type.seat_type, in3.distance) as price
     from (select in1.travel_id,
                  in1.station_id as depart_station_id,
                  in2.station_id as arrive_station_id,
                  in2.distance
           from (select *, row_number() over () as row1
                 from passing_station
                 order by travel_id, depart_time) in1

                    inner join (select *, row_number() over () as row2
                                from passing_station
                                order by travel_id, depart_time) in2
                               on row1 + 1 = row2
                                   and in1.travel_id = in2.travel_id) in3
              left join travel on in3.travel_id = travel.travel_id
              inner join seat_type on seat_type.first = substr(train_id, 1, 1));